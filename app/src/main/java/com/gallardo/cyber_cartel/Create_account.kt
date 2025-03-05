package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.util.Log
import com.gallardo.cyber_cartel.cb_api.RetrofitClient
import com.gallardo.cyber_cartel.cb_api.User
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Callback
import retrofit2.Response

class Create_account : AppCompatActivity() {

    private lateinit var alreadyhaveanaccount: TextView
    private lateinit var login: TextView
    private lateinit var confirm: Button
    private lateinit var et_username: EditText
    private lateinit var et_email: EditText
    private lateinit var et_pasword: EditText
    private lateinit var et_confirmpasswrod: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        et_username = findViewById(R.id.et_username_createAccountPage)
        et_email = findViewById(R.id.et_email_createAccountPage)
        et_pasword = findViewById(R.id.et_password_createAccountPage)
        et_confirmpasswrod = findViewById(R.id.et_Confirmpassword_createAccountPage)

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
        val password = et_pasword.text.toString().trim()
        val confirmPassword = et_confirmpasswrod.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            et_pasword.text.clear()
            et_confirmpasswrod.text.clear()
            return
        }

        val user = User(
            name = name,
            email = email,
            password = password,
            confirm_password = confirmPassword
        )
        val apiService = RetrofitClient.getService()
        val call = apiService.register(user)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: retrofit2.Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    // Save user to Firebase after successful MySQL registration
                    saveUserToFirebase(name, email)

                    startActivity(Intent(this@Create_account, Login_Page::class.java))
                    finish()
                    Toast.makeText(this@Create_account, "Registered successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@Create_account, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
                Toast.makeText(this@Create_account, "User Already Exists!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveUserToFirebase(name: String, email: String) {
        val database = FirebaseDatabase.getInstance("https://cybercartel-74e4s-default-rtdb.firebaseio.com/")
        val usersRef = database.getReference("users")
        val userId = usersRef.push().key

        if (userId != null) {
            val userMap = mapOf(
                "id" to userId,
                "name" to name,
                "email" to email
            )

            usersRef.child(userId).setValue(userMap)
                .addOnSuccessListener {
                    Log.d("Firebase", "User saved successfully")
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase", "Error saving user: ${e.message}")
                }
        }
    }
}
