package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class Rv_cart : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var cartAdapter : cart_adapter? = null
    private var productList = mutableListOf<cart_dataclass>()

    private lateinit var BackArrow : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_cart)

        productList = ArrayList()

        BackArrow = findViewById(R.id.Cart_backButton)

        BackArrow.setOnClickListener(){
            val intent = Intent(this, Rv_Home_Page::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById<View>(R.id.Cart_RecyclerView) as RecyclerView
        cartAdapter = cart_adapter(this@Rv_cart,productList)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
//        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = cartAdapter

        prepareProductListData()

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