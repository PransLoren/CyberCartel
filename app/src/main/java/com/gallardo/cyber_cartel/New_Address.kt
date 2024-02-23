package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class New_Address: AppCompatActivity() {

    private lateinit var img_back : ImageView
    private lateinit var btn_create_address : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_address)

        img_back = findViewById(R.id.back_to_address)
        img_back.setOnClickListener{
            val intent = Intent(this,User_Addresses::class.java)
            startActivity(intent)
            finish()
        }

        btn_create_address = findViewById(R.id.create_address_btn)
        btn_create_address.setOnClickListener{
            val intent = Intent(this,User_Addresses::class.java)
            startActivity(intent)
            finish()
        }
    }
}