package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Products
import com.gallardo.cyber_cartel.api.models.ProductsItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// API ===
const val BASE_URL = "https://fast-hollows-67866-0eecb0964658.herokuapp.com/"
// ===


class Rv_Home_Page : AppCompatActivity() {

    // API ===
    lateinit var myAdapter_Products: MyAdapter_Products
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var mainContainer: RecyclerView
    //

//    private var recyclerView : RecyclerView? = null
//    private var rv_homepage_Adapter : rv_homepage_Adapter? = null
//    private var productList = mutableListOf<Rv_hompage_dataclass>()
//


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

        // API ===

        // TOKEN
        val authToken = intent.getStringExtra("authToken")

        mainContainer = findViewById(R.id.mainContainer)
        mainContainer.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        mainContainer.layoutManager = linearLayoutManager

        getMyProducts()

        // OLD CLICK VIEW

//        myAdapter_Products.onItemClick = {
//            val intent = Intent(this, Product_Page::class.java)
////            intent.putExtra("products")
//            startActivity(intent)
//        }

        // ===
        // END OF API===

        cartlogo = findViewById(R.id.img_cart_homepage)
        bottomNaviation = findViewById(R.id.btnav_bottomNavigation_MyProfile)

        cartlogo.setOnClickListener(){
            // TOKEN
            intent.putExtra("authToken", authToken)

            val intent = Intent(this, Rv_cart::class.java)
            startActivity(intent)
        }

        bottomNaviation.setOnItemSelectedListener {
            when(it.itemId){
//                R.id.home -> {val intent = Intent(this, rvHompagee::class.java)
//                    startActivity(intent)
//                    finish()}

                R.id.home ->{val intent = Intent(this, Rv_Home_Page::class.java)
                    // TOKEN
                    intent.putExtra("authToken", authToken)

                    startActivity(intent)
                    finish()}

                R.id.me_profile -> {val  intent = Intent(this, My_Account::class.java)
                    // TOKEN
                    intent.putExtra("authToken", authToken)

                    startActivity(intent)
                    finish()}
            }
            true
        }
//
//
//        productList = ArrayList()
        categories_dataClass = ArrayList()

        homepagecategoriesRV = findViewById<View>(R.id.recyclerView_Categories) as RecyclerView
        home_page_categories_adapter = home_page_categories_adapter(this@Rv_Home_Page,categories_dataClass )
        val catlayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        homepagecategoriesRV!!.layoutManager = catlayoutManager
        homepagecategoriesRV!!.adapter = home_page_categories_adapter

        prepareiconListData()


//        recyclerView= findViewById<View>(R.id.mainContainer) as RecyclerView
//        rv_homepage_Adapter = rv_homepage_Adapter(this@Rv_Home_Page,productList)
//        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
//        recyclerView!!.layoutManager = layoutManager
//        recyclerView!!.adapter = rv_homepage_Adapter

//        prepareProductData()

    }

    // FOR API ===
    private fun getMyProducts(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getProducts()

        retrofitData.enqueue(object : Callback<List<ProductsItem>?> {
            override fun onResponse(
                call: Call<List<ProductsItem>?>,
                response: Response<List<ProductsItem>?>) {
                val responseBody = response.body()!!

                myAdapter_Products = MyAdapter_Products(baseContext, responseBody)
                myAdapter_Products.notifyDataSetChanged()
                mainContainer.adapter = myAdapter_Products
            }

            override fun onFailure(call: Call<List<ProductsItem>?>, t: Throwable) {
                d("HomePage", "onFailure" + t.message)
            }
        })
    }
    // ===



    private fun prepareiconListData() {
        var icon = home_page_categories_DC(R.drawable.processor)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.graphics_card)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.storage)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.motherboard_cat)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.psu)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.ram)
        categories_dataClass.add(icon)
        icon = home_page_categories_DC(R.drawable.chassis)
        categories_dataClass.add(icon)
    }
//
//
//    private fun prepareProductData() {
//        var product = Rv_hompage_dataclass("CPU", "10,000",R.drawable.cpu_product1, Product_Page::class.java)
//        productList.add(product)
//        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
//        productList.add(product)
//        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
//        productList.add(product)
//        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
//        productList.add(product)
//        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
//        productList.add(product)
//        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
//        productList.add(product)
//        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
//        productList.add(product)
//        product = Rv_hompage_dataclass("CPU", "10,000", R.drawable.cpu_product1, Product_Page::class.java)
//        productList.add(product)
//
//
//    }
}