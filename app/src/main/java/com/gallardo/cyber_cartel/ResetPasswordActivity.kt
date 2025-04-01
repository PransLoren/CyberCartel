package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var etNewPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSubmit: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        etNewPassword = findViewById(R.id.et_new_password)
        etConfirmPassword = findViewById(R.id.et_confirm_password)
        btnSubmit = findViewById(R.id.btn_reset_password)

        database = FirebaseDatabase.getInstance().getReference("users")

        val email = intent.getStringExtra("email")

        btnSubmit.setOnClickListener { resetPassword(email) }
    }

    private fun resetPassword(email: String?) {
        val newPassword = etNewPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (newPassword != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        if (email.isNullOrEmpty()) {
            Toast.makeText(this, "Email not found!", Toast.LENGTH_SHORT).show()
            return
        }

        // Find user by email and update password
        findUserByEmailAndUpdatePassword(email, newPassword)
    }

    private fun findUserByEmailAndUpdatePassword(email: String, newPassword: String) {
        database.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        userSnapshot.ref.child("password").setValue(newPassword)
                        Toast.makeText(this@ResetPasswordActivity, "Password updated successfully!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@ResetPasswordActivity, Login_Page::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this@ResetPasswordActivity, "Email not found in database!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ResetPasswordActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
