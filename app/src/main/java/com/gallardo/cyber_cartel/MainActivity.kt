package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

private lateinit var bb: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bb = findViewById(R.id.mainbt)

        bb.setOnClickListener() {
            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)

        }
    }
}