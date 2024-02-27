package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Rv_Home_Page : AppCompatActivity() {


    private var recyclerView : RecyclerView? = null
    private var rv_homepage_Adapter : rv_homepage_Adapter? = null
    private var productList = mutableListOf<Rv_hompage_dataclass>()

    //CATEGORIES PART
    private lateinit var homepagecategoriesRV : RecyclerView
    private var categories_dataClass = mutableListOf<home_page_categories_DC>()
    private var home_page_categories_adapter : home_page_categories_adapter? = null

    private lateinit var amdlogo : ImageView
    private lateinit var intellogo : ImageView
    private lateinit var cartlogo : ImageView
    private lateinit var bottomNaviation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_home_page)

        amdlogo = findViewById(R.id.img_amd_builder)
        intellogo = findViewById(R.id.img_intel_builder)
        cartlogo = findViewById(R.id.img_cart_homepage)
        bottomNaviation = findViewById(R.id.btnav_bottomNavigation_MyProfile)

        amdlogo.setOnClickListener(){
            val intent = Intent(this, Rv_amd_build::class.java)
            startActivity(intent)
        }

        intellogo.setOnClickListener(){
            val intent = Intent(this, Rv_intel_build::class.java)
            startActivity(intent)
        }
        cartlogo.setOnClickListener(){
            val intent = Intent(this, Rv_cart::class.java)
            startActivity(intent)
        }

        bottomNaviation.setOnItemSelectedListener {
            when(it.itemId){
//                R.id.home -> {val intent = Intent(this, rvHompagee::class.java)
//                    startActivity(intent)
//                    finish()}

                R.id.home ->{val intent = Intent(this, Rv_Home_Page::class.java)
                    startActivity(intent)
                    finish()}

                R.id.pre_built ->{val intent = Intent(this, Rv_amd_build::class.java)
                    startActivity(intent)
                    finish()}

                R.id.me_profile -> {val  intent = Intent(this, My_Account::class.java)
                    startActivity(intent)
                    finish()}
            }
            true
        }


        productList = ArrayList()
       categories_dataClass = ArrayList()

        homepagecategoriesRV = findViewById<View>(R.id.recyclerView_Categories) as RecyclerView
        home_page_categories_adapter = home_page_categories_adapter(this@Rv_Home_Page,categories_dataClass )
        val catlayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        homepagecategoriesRV!!.layoutManager = catlayoutManager
        homepagecategoriesRV!!.adapter = home_page_categories_adapter

        prepareiconListData()


        recyclerView= findViewById<View>(R.id.mainContainer) as RecyclerView
        rv_homepage_Adapter = rv_homepage_Adapter(this@Rv_Home_Page,productList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = rv_homepage_Adapter

        prepareProductData()
        
    }

    private fun prepareiconListData() {
       var icon = home_page_categories_DC(R.drawable.cpubg)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.gpubg)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.hddbg)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.mobobg)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.psubg)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.rambg)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.ssdbg)
        categories_dataClass.add(icon)
    }


    private fun prepareProductData() {
        var product = Rv_hompage_dataclass("CPU", "10,000",R.drawable.cpu_product1, Product_Page::class.java)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
        productList.add(product)
        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
        productList.add(product)


    }
}