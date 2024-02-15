package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Rv_amd_build : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var amdbuildAdapter : amdbuild_adapter? = null
    private var productList = mutableListOf<amdbuild_dataclass>()

    private lateinit var backbutton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_amd_build)

        backbutton = findViewById(R.id.img_backArrow_amd_build)

            backbutton.setOnClickListener(){
            val intent = Intent(this, Rv_Home_Page::class.java)
            startActivity(intent)
        }


        productList = ArrayList()

        recyclerView = findViewById<View>(R.id.rv_amd_build) as RecyclerView
        amdbuildAdapter = amdbuild_adapter(this@Rv_amd_build, productList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2 )
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = amdbuildAdapter

        prepareProductListData()
    }

    private fun prepareProductListData() {
        var product = amdbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = amdbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = amdbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = amdbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = amdbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = amdbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = amdbuild_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
    }


}