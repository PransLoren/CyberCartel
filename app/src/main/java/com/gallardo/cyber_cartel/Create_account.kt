package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Create_account : AppCompatActivity() {

    private lateinit var alreadyhaveanaccount: TextView
    private lateinit var login: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        alreadyhaveanaccount = findViewById(R.id.tv_alreadyHave_anAccount)
        login = findViewById(R.id.tv_already_have_an_account_logIn)

        alreadyhaveanaccount.setOnClickListener (){
            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)
        }

        login.setOnClickListener (){
            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)
        }
    }
}