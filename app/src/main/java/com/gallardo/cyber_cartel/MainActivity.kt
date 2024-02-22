package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

private lateinit var bb: Button

private fun Any.postDelayed(any: Any?) {

}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({val intent = Intent(this, Rv_Home_Page::class.java)
            startActivity(intent)
            finish()}, 2000)

//        bb = findViewById(R.id.mainbt)

//        bb.setOnClickListener() {
//            val intent = Intent(this, Login_Page::class.java)
//            startActivity(intent)
//
//        }
    }
}