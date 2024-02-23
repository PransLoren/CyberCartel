package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Change_Password: AppCompatActivity() {
    private lateinit var img_bck : ImageView
    private lateinit var change_password : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_account_change_password)

        img_bck = findViewById(R.id.back_to_profile)
        img_bck.setOnClickListener {
            val intent = Intent(this, My_Account::class.java)
            startActivity(intent)
            finish()
        }

        change_password = findViewById(R.id.change_password_btn)
        change_password.setOnClickListener {
            val intent = Intent(this, My_Account::class.java)
            startActivity(intent)
            finish()
        }
    }
}