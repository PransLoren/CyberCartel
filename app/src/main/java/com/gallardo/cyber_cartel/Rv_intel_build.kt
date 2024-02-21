package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Rv_intel_build : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var intelbuildAdapter : intelbuild_adapter? = null
    private var productList = mutableListOf<intelbuild_dataclass>()

    private lateinit var backbutton : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_intel_build)

        backbutton = findViewById(R.id.img_backArrow_intel_builder)

        backbutton.setOnClickListener(){
            val intent = Intent(this, Rv_Home_Page::class.java)
            startActivity(intent)
        }

        productList = ArrayList()

        recyclerView = findViewById<View>(R.id.rv_intel_builder) as RecyclerView
        intelbuildAdapter = intelbuild_adapter(this@Rv_intel_build, productList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = intelbuildAdapter

        prepareProductListData()


    }

    private fun prepareProductListData() {
        var product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = intelbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)

    }
}