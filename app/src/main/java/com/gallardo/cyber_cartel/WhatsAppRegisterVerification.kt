package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.Credentials.basic
import java.io.IOException

class WhatsAppRegisterVerification : AppCompatActivity() {
    private lateinit var etPhoneNumber: EditText
    private lateinit var etVerificationCode: EditText
    private lateinit var btnSendCode: Button
    private lateinit var btnVerifyCode: Button
    private var generatedOtp: String? = null
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whatsapp_verification)

        etPhoneNumber = findViewById(R.id.et_phone_number)
        etVerificationCode = findViewById(R.id.et_verification_code)
        btnSendCode = findViewById(R.id.btn_send_code)
        btnVerifyCode = findViewById(R.id.btn_verify_code)

        val phoneNumber = intent.getStringExtra("phone_number")
        phoneNumber?.let {
            etPhoneNumber.setText(it)
        }

        btnSendCode.setOnClickListener { sendVerificationCode() }
        btnVerifyCode.setOnClickListener { verifyCode() }
    }

    private fun sendVerificationCode() {
        val phoneNumber = etPhoneNumber.text.toString().trim()
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show()
            return
        }

        generatedOtp = ((Math.random() * 9000).toInt() + 1000).toString()
        val message = "Your verification code is: $generatedOtp"

        val body: RequestBody = FormBody.Builder()
            .add("To", "whatsapp:$phoneNumber")
            .add("From", TWILIO_WHATSAPP_NUMBER)
            .add("Body", message)
            .build()

        val request: Request = Request.Builder()
            .url("https://api.twilio.com/2010-04-01/Accounts/$TWILIO_SID/Messages.json")
            .header("Authorization", basic(TWILIO_SID, TWILIO_AUTH_TOKEN))
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@WhatsAppRegisterVerification, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    Toast.makeText(this@WhatsAppRegisterVerification, "Code sent successfully", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun verifyCode() {
        val code = etVerificationCode.text.toString().trim()
        if (code == generatedOtp) {
            Toast.makeText(this, "Verification successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Invalid code", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        private const val TWILIO_SID = "AC9130764078147ce2fd85d696c85c8121"
        private const val TWILIO_AUTH_TOKEN = "46c2a99bc4d7845d3e9b8245b710523d"
        private const val TWILIO_WHATSAPP_NUMBER = "whatsapp:+14155238886"
    }
}
