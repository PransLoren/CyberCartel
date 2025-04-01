package com.gallardo.cyber_cartel

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etVerificationCode: EditText
    private lateinit var btnSendCode: Button
    private lateinit var btnVerifyCode: Button
    private lateinit var sharedPreferences: SharedPreferences
    private val client = OkHttpClient()
    private var generatedOtp: String? = null
    private var enteredEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        etEmail = findViewById(R.id.et_email_forgot)
        etVerificationCode = findViewById(R.id.et_verification_code)
        btnSendCode = findViewById(R.id.btn_send_code)
        btnVerifyCode = findViewById(R.id.btn_verify_code)

        sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)

        // Initially, hide the verification section
        etVerificationCode.visibility = View.GONE
        btnVerifyCode.visibility = View.GONE

        btnSendCode.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Generate OTP
            generatedOtp = (100000..999999).random().toString()
            enteredEmail = email

            // Store OTP in SharedPreferences for verification later
            sharedPreferences.edit().putString("storedOtp", generatedOtp).apply()

            // Show a toast message before sending the email
            Toast.makeText(this, "Sending verification code to $email...", Toast.LENGTH_SHORT).show()

            sendVerificationEmail(email, generatedOtp!!)
        }


        btnVerifyCode.setOnClickListener {
            val enteredOtp = etVerificationCode.text.toString().trim()
            val storedOtp = sharedPreferences.getString("storedOtp", null)

            if (enteredOtp == storedOtp) {
                Toast.makeText(this, "Verification Successful!", Toast.LENGTH_SHORT).show()

                // Ensure enteredEmail is not null
                enteredEmail?.let { email ->
                    val intent = Intent(this, ResetPasswordActivity::class.java)
                    intent.putExtra("email", email) // Pass email to ResetPasswordActivity
                    startActivity(intent)
                    finish()
                } ?: Toast.makeText(this, "Error: Email not found!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid Code!", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun sendVerificationEmail(email: String, otp: String) {
        val apiKey = ""
        val senderEmail = "frdo.opu-an.up@phinmaed.com"

        val personalizationsArray = JSONArray().apply {
            put(JSONObject().apply {
                put("to", JSONArray().apply {
                    put(JSONObject().put("email", email))
                })
                put("subject", "Cyber Cartel Password Reset Code")
            })
        }

        val contentArray = JSONArray().apply {
            put(JSONObject().apply {
                put("type", "text/plain")
                put("value", "Your password reset code is: $otp")
            })
        }

        val emailData = JSONObject().apply {
            put("personalizations", personalizationsArray)
            put("from", JSONObject().put("email", senderEmail))
            put("content", contentArray)
        }

        val requestBody = emailData.toString().toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = Request.Builder()
            .url("https://api.sendgrid.com/v3/mail/send")
            .header("Authorization", "Bearer $apiKey")
            .header("Content-Type", "application/json")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@ForgotPasswordActivity, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ForgotPasswordActivity, "OTP sent to $email", Toast.LENGTH_SHORT).show()

                        // Show the verification section
                        etVerificationCode.visibility = View.VISIBLE
                        btnVerifyCode.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(this@ForgotPasswordActivity, "Failed to send OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
