package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import okhttp3.*
import java.io.IOException

class Login_Page : AppCompatActivity() {

    private lateinit var emailField: EditText
    private lateinit var phoneField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button
    private lateinit var createAccountText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        emailField = findViewById(R.id.et_email_logInPage)
        phoneField = findViewById(R.id.et_phone_logInPage)
        passwordField = findViewById(R.id.et_passwordLogIn)
        loginButton = findViewById(R.id.bt_logIn)
        createAccountText = findViewById(R.id.tv_createAcc)

        loginButton.setOnClickListener {
            validateUser()
        }

        createAccountText.setOnClickListener {
            val intent = Intent(this, Create_account::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateUser() {
        val email = emailField.text.toString().trim()
        var phone = phoneField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            showToast("Please fill in all required fields")
            return
        }

        if (phone.isNotEmpty() && phone.length == 11 && phone.startsWith("09")) {
            phone = "+63" + phone.substring(1)
        } else if (phone.isNotEmpty() && !phone.startsWith("+63")) {
            showToast("Invalid phone number format!")
            return
        }

        checkUserInXampp(email, phone, password)
    }

    private fun checkUserInXampp(email: String, phone: String, password: String) {
        val client = OkHttpClient()
        val formBody = FormBody.Builder()
            .add("email", email)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url("http://192.168.206.88/cybercartel/login.php")
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
                runOnUiThread {
                    if (response.isSuccessful && responseBody == "Login successful") {
                        checkUserInFirebase(email, phone, password)
                    } else {
                        showToast("Invalid credentials or user not found")
                    }
                }
            }
        })
    }

    private fun checkUserInFirebase(email: String, phone: String, password: String) {
        val database = Firebase.database("https://cybercartel-74e4s-default-rtdb.firebaseio.com/")
        val usersRef = database.getReference("users")

        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val storedPassword = userSnapshot.child("password").getValue(String::class.java)
                        val userId = userSnapshot.child("id").getValue(String::class.java)
                        val storedPhone = userSnapshot.child("phone").getValue(String::class.java)

                        if (storedPassword == password) {
                            if (storedPhone.isNullOrEmpty() && phone.isNotEmpty()) {
                                usersRef.child(userSnapshot.key!!).child("phone").setValue(phone)
                            }

                            navigateToVerification(userId ?: "")
                            return
                        }
                    }
                    showToast("Incorrect password")
                } else {
                    showToast("User not found in Firebase")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast("Database error: ${error.message}")
            }
        })
    }

    private fun navigateToVerification(userId: String) {
        showToast("Login successful! Redirecting to OTP verification.")
        val intent = Intent(this, LoginVerification::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}