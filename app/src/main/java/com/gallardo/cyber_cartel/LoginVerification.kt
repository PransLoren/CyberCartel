package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.database.*
import okhttp3.*
import java.io.IOException

class LoginVerification : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private var generatedOtp: String = ""
    private lateinit var userPhoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_verification)


        database = FirebaseDatabase.getInstance("https://cybercartel-74e4s-default-rtdb.firebaseio.com/").reference


        val otpEditText = findViewById<EditText>(R.id.et_Otp)
        val verifyButton = findViewById<Button>(R.id.btnVerify)
        val cancelTextView = findViewById<TextView>(R.id.cancelTv)


        val userId = intent.getStringExtra("userId")
        if (!userId.isNullOrEmpty()) {
            fetchUserPhoneNumber(userId)
        } else {
            Toast.makeText(this, "User ID not found!", Toast.LENGTH_LONG).show()
            finish()
        }


        otpEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val isValid = s?.length == 6
                verifyButton.isEnabled = isValid
                verifyButton.backgroundTintList = ContextCompat.getColorStateList(
                    this@LoginVerification,
                    if (isValid) R.color.enabled_button_color else android.R.color.darker_gray
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Verify button
        verifyButton.setOnClickListener {
            verifyOtp(otpEditText.text.toString())
        }

        // Cancel button (optional)
        cancelTextView.setOnClickListener {
            finish()
        }
    }

    // ✅ Fetch user's phone number from Firebase
    private fun fetchUserPhoneNumber(userId: String) {
        database.child("users").child(userId).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    userPhoneNumber = snapshot.child("phone").value.toString()
                    sendOtp(userPhoneNumber)
                } else {
                    Toast.makeText(this, "User not found in database!", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error fetching user data: ${e.message}", Toast.LENGTH_LONG).show()
                finish()
            }
    }

    // ✅ Function to send OTP via Twilio API
    private fun sendOtp(phone: String) {
        val formattedPhone = when {
            phone.startsWith("+63") -> phone
            phone.startsWith("09") -> "+63" + phone.substring(1)
            else -> phone
        }

        Log.d("Twilio", "Sending OTP to: $formattedPhone") // Debugging

        generatedOtp = (100000..999999).random().toString()

        val twilioAccountSid = "AC9130764078147ce2fd85d696c85c8121"
        val twilioAuthToken = "46c2a99bc4d7845d3e9b8245b710523d"
        val messagingServiceSid = "MG4a40cf76c5c7a8ef2e5e79a29909cf13"

        val client = OkHttpClient()

        val requestBody = FormBody.Builder()
            .add("To", formattedPhone)
            .add("MessagingServiceSid", messagingServiceSid)
            .add("Body", "Your Cyber Cartel OTP is: $generatedOtp")
            .build()

        val request = Request.Builder()
            .url("https://api.twilio.com/2010-04-01/Accounts/$twilioAccountSid/Messages.json")
            .post(requestBody)
            .header("Authorization", Credentials.basic(twilioAccountSid, twilioAuthToken))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@LoginVerification, "Failed to send OTP: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    if (response.isSuccessful) {
                        Toast.makeText(this@LoginVerification, "OTP sent to $formattedPhone", Toast.LENGTH_LONG).show()
                        Log.d("OTP", "Generated OTP: $generatedOtp")
                    } else {
                        Toast.makeText(this@LoginVerification, "Failed to send OTP: ${response.code}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }


    private fun verifyOtp(enteredOtp: String) {
        if (enteredOtp == generatedOtp) {
            Toast.makeText(this, "OTP Verified!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, Rv_Home_Page::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Incorrect OTP, please try again.", Toast.LENGTH_LONG).show()
        }
    }
}
