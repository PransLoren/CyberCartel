package com.gallardo.cyber_cartel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Rv_Home_Page : AppCompatActivity() {


    private var recyclerView : RecyclerView? = null
    private var rv_homepage_Adapter : rv_homepage_Adapter? = null
    private var productList = mutableListOf<Rv_hompage_dataclass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_home_page)


        productList = ArrayList()

        recyclerView= findViewById<View>(R.id.mainContainer) as RecyclerView
        rv_homepage_Adapter = rv_homepage_Adapter(this@Rv_Home_Page,productList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = rv_homepage_Adapter

        prepareProductData()
        
    }


    private fun prepareProductData() {
        var product = Rv_hompage_dataclass("CPU", "10,000P",R.drawable.cpu_product1)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000P", R.drawable.cpu_product1)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000P", R.drawable.cpu_product1)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000P", R.drawable.cpu_product1)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000P", R.drawable.cpu_product1)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000P", R.drawable.cpu_product1)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000P", R.drawable.cpu_product1)
        productList.add(product)


    }
}