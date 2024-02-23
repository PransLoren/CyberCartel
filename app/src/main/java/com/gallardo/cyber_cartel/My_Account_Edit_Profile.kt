package com.gallardo.cyber_cartel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class My_Account_Edit_Profile: AppCompatActivity() {

    private lateinit var bg_img : ImageView
    private lateinit var profile_img : ImageView
    private lateinit var username_et : EditText
    private lateinit var email_et : EditText
    private lateinit var save_btn : TextView
    private lateinit var img_back : ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)

        save_btn = findViewById(R.id.tv_SaveButton_Account)
        save_btn.setOnClickListener{
            val intent = Intent(this,My_Account::class.java)
            startActivity(intent)
            finish()
        }

        img_back = findViewById(R.id.back_to_profile)
        img_back.setOnClickListener{
            val intent = Intent(this,My_Account::class.java)
            startActivity(intent)
            finish()
        }
    }
}