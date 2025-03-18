package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gallardo.cyber_cartel.cb_api.SharedPreferencesManager
import com.google.firebase.database.*

class Login_Page : AppCompatActivity() {

    private lateinit var createAccount: TextView
    private lateinit var button: Button
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var password: EditText
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        createAccount = findViewById(R.id.tv_createAcc)
        button = findViewById(R.id.bt_logIn)
        email = findViewById(R.id.et_email_logInPage)
        phone = findViewById(R.id.et_phone_logInPage) // 🔹 Added Phone Number Field
        password = findViewById(R.id.et_passwordLogIn)

        database = FirebaseDatabase.getInstance("https://cybercartel-74e4s-default-rtdb.firebaseio.com/").reference

        createAccount.setOnClickListener {
            val intent = Intent(this, Create_account::class.java)
            startActivity(intent)
            finish()
        }

        button.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val userEmail = email.text.toString().trim()
        var userPhone = phone.text.toString().trim()
        val userPassword = password.text.toString().trim()

        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        // ✅ Validate phone number format (convert to +63XXXXXXXXXX)
        if (userPhone.isNotEmpty() && userPhone.length == 11 && userPhone.startsWith("09")) {
            userPhone = "+63" + userPhone.substring(1)
        } else if (userPhone.isNotEmpty()) {
            Toast.makeText(this, "Invalid phone number format!", Toast.LENGTH_SHORT).show()
            return
        }

        database.child("users").orderByChild("email").equalTo(userEmail)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnap in snapshot.children) {
                            val storedPassword = userSnap.child("password").value.toString()
                            val userId = userSnap.child("id").value.toString()
                            val username = userSnap.child("name").value.toString()
                            val storedPhone = userSnap.child("phone").value?.toString()

                            if (storedPassword == userPassword) {
                                // ✅ Save phone number ONLY IF it's not already stored
                                if (storedPhone.isNullOrEmpty() && userPhone.isNotEmpty()) {
                                    database.child("users").child(userSnap.key!!)
                                        .child("phone").setValue(userPhone)
                                }

                                SharedPreferencesManager.saveUserDetails(
                                    this@Login_Page,
                                    userId,
                                    username,
                                    userEmail
                                )

                                val intent = Intent(this@Login_Page, LoginVerification::class.java)
                                intent.putExtra("userId", userId)
                                startActivity(intent)
                                finish()
                                return
                            } else {
                                Toast.makeText(this@Login_Page, "Invalid password", Toast.LENGTH_SHORT).show()
                                return
                            }
                        }
                    } else {
                        Toast.makeText(this@Login_Page, "User not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Login_Page, "Firebase error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
