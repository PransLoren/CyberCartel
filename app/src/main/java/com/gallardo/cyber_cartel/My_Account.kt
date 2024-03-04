package com.gallardo.cyber_cartel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class My_Account: AppCompatActivity() {

    private lateinit var profile_pic : ImageView
    private lateinit var background_pic : ImageView
    private lateinit var tv_all : TextView
    private lateinit var tv_bought : TextView
    private lateinit var tv_refunded : TextView
    private lateinit var tv_cancelled : TextView
    private lateinit var img_all : ImageView
    private lateinit var img_bought : ImageView
    private lateinit var img_refunded : ImageView
    private lateinit var img_cancelled : ImageView
    private lateinit var tv_edit_profile : TextView
    private lateinit var my_purchase_history : TextView
    private lateinit var address : TextView
    private lateinit var change_pass : TextView
    private lateinit var bottomNaviation : BottomNavigationView
    private lateinit var backbutton : ImageView
    private lateinit var cart : ImageView
    private lateinit var logout : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_account)

        bottomNaviation = findViewById(R.id.btnav_bottomNavigation_MyProfile)
        backbutton = findViewById(R.id.back_to_profile)


        cart = findViewById(R.id.Cart)
        cart.setOnClickListener(){
            val intent = Intent(this,Rv_cart::class.java)
            intent.putExtra("previous_activity", "My_Account")
            startActivity(intent)
        }

        backbutton.setOnClickListener{
            val intent = Intent(this, Rv_Home_Page::class.java)
            startActivity(intent)
        }

        bottomNaviation.setOnItemSelectedListener {
            when(it.itemId){
//                R.id.home -> {val intent = Intent(this, rvHompagee::class.java)
//                    startActivity(intent)
//                    finish()}

                R.id.me_profile -> {val  intent = Intent(this, My_Account::class.java)
                    startActivity(intent)
                    finish()}

                R.id.home ->{val intent = Intent(this, Rv_Home_Page::class.java)
                    startActivity(intent)
                    finish()}
            }
            true
        }

        change_pass = findViewById(R.id.change_password)
        change_pass.setOnClickListener {
            val intent = Intent(this, Change_Password::class.java)
            startActivity(intent)
            finish()
        }

        address = findViewById(R.id.address)
        address.setOnClickListener {
            val intent = Intent(this, User_Addresses::class.java)
            startActivity(intent)
            finish()
        }

        tv_all = findViewById<TextView>(R.id.all_tv)
        tv_all.setOnClickListener{
            val intent = Intent(this, My_Purchase_All::class.java)
            startActivity(intent)
            finish()
        }

        tv_bought = findViewById<TextView>(R.id.bought_tv)
        tv_bought.setOnClickListener{
            val intent = Intent(this, My_Purchase_Bought::class.java)
            startActivity(intent)
            finish()
        }

        tv_cancelled = findViewById<TextView>(R.id.cancelled_tv)
        tv_cancelled.setOnClickListener{
            val intent = Intent(this, My_Purchase_Cancelled::class.java)
            startActivity(intent)
            finish()
        }

        tv_refunded = findViewById<TextView>(R.id.refunded_tv)
        tv_refunded.setOnClickListener{
            val intent = Intent(this, My_Purchase_Refunded::class.java)
            startActivity(intent)
            finish()
        }

        my_purchase_history = findViewById<TextView>(R.id.view_all_purchase)
        my_purchase_history.setOnClickListener{
            val intent = Intent(this, My_Purchase_All::class.java)
            startActivity(intent)
            finish()
        }

        img_all = findViewById(R.id.all_img)
        img_all.setOnClickListener{
            val intent = Intent(this, My_Purchase_All::class.java)
            startActivity(intent)
            finish()
        }

        img_bought = findViewById(R.id.bought_img)
        img_bought.setOnClickListener{
            val intent = Intent(this, My_Purchase_Bought::class.java)
            startActivity(intent)
            finish()
        }

        img_cancelled = findViewById(R.id.cancelled_img)
        img_cancelled.setOnClickListener{
            val intent = Intent(this, My_Purchase_Cancelled::class.java)
            startActivity(intent)
            finish()
        }

        img_refunded = findViewById(R.id.refunded_img)
        img_refunded.setOnClickListener{
            val intent = Intent(this, My_Purchase_Refunded::class.java)
            startActivity(intent)
            finish()
        }

        logout = findViewById(R.id.LogOut_MyProfile)
        logout.setOnClickListener {
            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)
            finish()
        }
    }
}
