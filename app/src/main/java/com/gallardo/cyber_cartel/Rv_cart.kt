package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class Rv_cart : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var cartAdapter : cart_adapter? = null
    private var productList = mutableListOf<cart_dataclass>()

    private lateinit var BackArrow : ImageView
    private lateinit var checkOut : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_cart)

        productList = ArrayList()

        BackArrow = findViewById(R.id.Cart_backButton)
        BackArrow.setOnClickListener {
            navigateBack()
        }

        checkOut = findViewById(R.id.checkOut_Button)
        checkOut.setOnClickListener{
            val intent = Intent(this,Checkout::class.java)
            intent.putExtra("previous_activity", "Checkout")
            startActivity(intent)
            finish()
        }

        recyclerView = findViewById<View>(R.id.Cart_RecyclerView) as RecyclerView
        cartAdapter = cart_adapter(this@Rv_cart,productList)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
//        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = cartAdapter

        prepareProductListData()

    }

    private fun navigateBack() {
        val previousActivity = intent.getStringExtra("previous_activity")
        when (previousActivity) {
            "Category_Cpu" -> {
                startActivity(Intent(this, Category_Cpu::class.java))
            }
            "Category_Gpu" -> {
                startActivity(Intent(this, Category_Gpu::class.java))
            }
            "Category_Hdd" -> {
                startActivity(Intent(this, Category_Hdd::class.java))
            }
            "Category_MoBo" -> {
                startActivity(Intent(this, Category_MoBo::class.java))
            }
            "Category_Ssd" -> {
                startActivity(Intent(this, Category_Ssd::class.java))
            }
            "Category_Ram" -> {
                startActivity(Intent(this, Category_Ram::class.java))
            }
            "Category_Psu" -> {
                startActivity(Intent(this, Category_Psu::class.java))
            }
            "Cart" -> {
                startActivity(Intent(this, Product_Page::class.java))
            }
            "My_Account" -> {
                startActivity(Intent(this, My_Account::class.java))
            }
            "My_Purchase_All" -> {
                startActivity(Intent(this, My_Purchase_All::class.java))
            }
            "My_Purchase_Bought" -> {
                startActivity(Intent(this, My_Purchase_Bought::class.java))
            }
            "My_Purchase_Cancelled" -> {
                startActivity(Intent(this, My_Purchase_Cancelled::class.java))
            }
            "My_Purchase_Refunded" -> {
                startActivity(Intent(this, My_Purchase_Refunded::class.java))
            }
            "Amd_Build" -> {
                startActivity(Intent(this, Rv_amd_build::class.java))
            }
            "Intel_Build" -> {
                startActivity(Intent(this, Rv_intel_build::class.java))
            }
            else -> {
                // Handle unknown previous activity or default behavior
                // For example, navigate back to the home screen
                startActivity(Intent(this, Rv_Home_Page::class.java))
            }
        }
        finish()
    }

    private fun prepareProductListData() {
        var product = cart_dataclass("AMD INTELCORE 15GEN PROCESSOR", "12,000","okay",R.drawable.cpu_product1,"1")
        productList.add(product)
        product = cart_dataclass("cpu", "12,000","okay",R.drawable.cpu_product1,"1")
        productList.add(product)
        product = cart_dataclass("cpu", "12,000","okay",R.drawable.cpu_product1,"1")
        productList.add(product)
        product = cart_dataclass("cpu", "12,000","okay",R.drawable.cpu_product1,"1")
        productList.add(product)
        product = cart_dataclass("cpu", "12,000","okay",R.drawable.cpu_product1,"1")
        productList.add(product)
    }
}