package com.gallardo.cyber_cartel


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login_Page : AppCompatActivity() {

    private lateinit var createAccount: TextView
    private lateinit var button : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        createAccount = findViewById(R.id.tv_createAcc)
        button = findViewById(R.id.bt_logIn)


        createAccount.setOnClickListener (){
            val intent = Intent(this, Create_account::class.java)
            startActivity(intent)
        }

        button.setOnClickListener(){
           val intent = Intent(this,Rv_Home_Page ::class.java )
            startActivity(intent)
        }


    }
}
