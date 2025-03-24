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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        et_username = findViewById(R.id.et_username_createAccountPage)
        et_email = findViewById(R.id.et_email_createAccountPage)
        et_password = findViewById(R.id.et_password_createAccountPage)
        et_confirmpassword = findViewById(R.id.et_Confirmpassword_createAccountPage)

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

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showToast("Please fill in all fields")
            return
        }

        if (password != confirmPassword) {
            showToast("Passwords do not match")
            et_password.text.clear()
            et_confirmpassword.text.clear()
            return
        }

        sendRegistrationToXampp(name, email, password)
    }

    private fun sendRegistrationToXampp(name: String, email: String, password: String) {
        val client = OkHttpClient()
        val formBody = FormBody.Builder()
            .add("name", name)
            .add("email", email)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url("http://192.168.206.88/cybercartel/register.php")
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    showToast("Error connecting to server: ${e.message}")
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                Log.d("RegisterDebug", "Server Response: $responseBody")

                runOnUiThread {
                    if (response.isSuccessful && responseBody == "Registration successful") {
                        saveUserToFirebase(name, email, password)
                    } else {
                        showToast("Registration failed: $responseBody")
                    }
                }
            }
        })
    }

    private fun saveUserToFirebase(name: String, email: String, password: String) {
        val database = FirebaseDatabase.getInstance("https://cybercartel-74e4s-default-rtdb.firebaseio.com/")
        val usersRef = database.getReference("users")
        val userId = usersRef.push().key

        if (userId != null) {
            val userMap = mapOf(
                "id" to userId,
                "name" to name,
                "email" to email,
                "password" to password
            )

            usersRef.child(userId).setValue(userMap)
                .addOnSuccessListener {
                    Log.d("Firebase", "User saved successfully")
                    navigateToLogin()
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase", "Error saving user: ${e.message}")
                }
        }
    }

    private fun navigateToLogin() {
        showToast("Registered successfully")
        startActivity(Intent(this, Login_Page::class.java))
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}