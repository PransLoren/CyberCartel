package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Realtime Database
        val database = Firebase.database("https://cybercartel-74e4f-default-rtdb.asia-southeast1.firebasedatabase.app/")

        //Example of writing data to the database after the delay
        Handler().postDelayed({
            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)
            finish()

            // Write data to the database after the splash screen.
            val myRef = database.getReference("myData")
            val dataToWrite = mapOf("message" to "App Started")
            myRef.setValue(dataToWrite)
                .addOnSuccessListener {
                    Log.d("Firebase", "Data written successfully")
                }
                .addOnFailureListener {
                    Log.e("Firebase", "Error writing data: ${it.message}")
                }
        }, 2000)
    }
}
