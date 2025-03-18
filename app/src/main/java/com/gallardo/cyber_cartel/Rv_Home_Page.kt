package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.ImageView
import android.widget.Toast
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

const val BASE_URL = "https://floppy-bobcats-admire.loca.lt/"

class Rv_Home_Page : AppCompatActivity() {

    lateinit var myAdapter_Products: MyAdapter_Products
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var mainContainer: RecyclerView

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

        val authToken = intent.getStringExtra("authToken")

        val intent = Intent(this@Rv_Home_Page, MyAdapter_Products::class.java)
        intent.putExtra("authToken", authToken)

        mainContainer = findViewById(R.id.mainContainer)
        mainContainer.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        mainContainer.layoutManager = linearLayoutManager

        getMyProducts()

        cartlogo = findViewById(R.id.img_cart_homepage)
        bottomNaviation = findViewById(R.id.btnav_bottomNavigation_MyProfile)

        cartlogo.setOnClickListener(){
            val intent = Intent(this, Rv_cart::class.java)
            intent.putExtra("authToken", authToken)
            startActivity(intent)
        }

        bottomNaviation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    val intent = Intent(this, Rv_Home_Page::class.java)
                    intent.putExtra("authToken", authToken)
                    startActivity(intent)
                    finish()
                }
                R.id.me_profile -> {
                    val intent = Intent(this, My_Account::class.java)
                    intent.putExtra("authToken", authToken)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }

        categories_dataClass = ArrayList()

        homepagecategoriesRV = findViewById<View>(R.id.recyclerView_Categories) as RecyclerView
        home_page_categories_adapter = home_page_categories_adapter(this@Rv_Home_Page, categories_dataClass)
        val catlayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        homepagecategoriesRV!!.layoutManager = catlayoutManager
        homepagecategoriesRV!!.adapter = home_page_categories_adapter

        prepareiconListData()
    }

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
}
