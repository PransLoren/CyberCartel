package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import okhttp3.*
import java.io.IOException

class Create_account : AppCompatActivity() {

    private lateinit var alreadyhaveanaccount: TextView
    private lateinit var login: TextView
    private lateinit var confirm: Button
    private lateinit var et_username: EditText
    private lateinit var et_email: EditText
    private lateinit var et_password: EditText
    private lateinit var et_confirmpassword: EditText
    private lateinit var et_phone: EditText // Added phone number field

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        et_username = findViewById(R.id.et_username_createAccountPage)
        et_email = findViewById(R.id.et_email_createAccountPage)
        et_password = findViewById(R.id.et_password_createAccountPage)
        et_confirmpassword = findViewById(R.id.et_Confirmpassword_createAccountPage)
        et_phone = findViewById(R.id.et_phone_createAccountPage) // Initialize phone field

        alreadyhaveanaccount = findViewById(R.id.tv_alreadyHave_anAccount)
        login = findViewById(R.id.tv_already_have_an_account_logIn)
        confirm = findViewById(R.id.bt_createAccount)

        alreadyhaveanaccount.setOnClickListener {
            startActivity(Intent(this, Login_Page::class.java))
            finish()
        }

        login.setOnClickListener {
            startActivity(Intent(this, Login_Page::class.java))
            finish()
        }

        confirm.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = et_username.text.toString().trim()
        val email = et_email.text.toString().trim()
        val password = et_password.text.toString().trim()
        val confirmPassword = et_confirmpassword.text.toString().trim()
        var phoneNumber = et_phone.text.toString().trim() // Get phone number

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
            showToast("Please fill in all fields")
            return
        }

        // Phone number validation & formatting
        if (phoneNumber.length == 11 && phoneNumber.startsWith("09")) {
            phoneNumber = "+63" + phoneNumber.substring(1)
        } else if (!phoneNumber.startsWith("+63")) {
            showToast("Invalid phone number format!")
            return
        }

        if (password != confirmPassword) {
            showToast("Passwords do not match")
            et_password.text.clear()
            et_confirmpassword.text.clear()
            return

        }
        saveUserToFirebase(name, email, password, phoneNumber)

    }



    private fun saveUserToFirebase(name: String, email: String, password: String, phoneNumber: String) {
        val database = FirebaseDatabase.getInstance("https://cybercartel-74e4s-default-rtdb.firebaseio.com/")
        val usersRef = database.getReference("users")
        val userId = usersRef.push().key

        if (userId != null) {
            val userMap = mapOf(
                "id" to userId,
                "name" to name,
                "email" to email,
                "password" to password,
                "phone" to phoneNumber // Save phone number
            )

            usersRef.child(userId).setValue(userMap)
                .addOnSuccessListener {
                    Log.d("Firebase", "User saved successfully")
                    navigateToWhatsAppVerification(phoneNumber)
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase", "Error saving user: ${e.message}")
                }
        }
    }

    private fun navigateToWhatsAppVerification(phoneNumber: String) {
        showToast("Registered successfully. Verifying WhatsApp...")
        val intent = Intent(this, WhatsAppRegisterVerification::class.java)
        intent.putExtra("phone_number", phoneNumber)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
