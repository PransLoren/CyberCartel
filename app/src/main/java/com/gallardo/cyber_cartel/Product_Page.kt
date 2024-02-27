package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Product_Page: AppCompatActivity(){

    private lateinit var productName : TextView
    private lateinit var productPrice : TextView
    private lateinit var productDescription : TextView

    private lateinit var img_back : ImageView
    private lateinit var product_cart : Button
    private lateinit var cartz : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        img_back = findViewById(R.id.back_btn)
        img_back.setOnClickListener{
            val intent = Intent(this,Rv_Home_Page::class.java)
            startActivity(intent)
            finish()
        }

        product_cart = findViewById(R.id.product_addToCart)
        product_cart.setOnClickListener{
            val intent = Intent(this,Rv_cart::class.java)
            startActivity(intent)
            finish()
        }

        cartz = findViewById(R.id.Cart)
        cartz.setOnClickListener{
            val intent = Intent(this,Rv_cart::class.java)
            startActivity(intent)
            finish()
        }
    }
}