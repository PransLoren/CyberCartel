package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.Category.PSU_Adapter
import com.gallardo.cyber_cartel.DataClass.Category.PSU_DC
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Categories
import com.gallardo.cyber_cartel.api.models.ProductsItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Category_Psu: AppCompatActivity() {

    // API ===
    lateinit var myAdapter_Category_CPU: MyAdapter_Categories
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var category_psu_rv: RecyclerView
    //

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAddressAdapter: PSU_Adapter
    private var allitemlist = ArrayList<PSU_DC>()
    private lateinit var img_back : ImageView
    private lateinit var cart_btn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_psu)

        // API ===
        category_psu_rv = findViewById(R.id.category_psu_rv)
        category_psu_rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        category_psu_rv.layoutManager = linearLayoutManager
        getPSUS()
        // ===

        img_back = findViewById(R.id.back_to_home)
        img_back.setOnClickListener{
            navigateBack()
        }

        cart_btn = findViewById(R.id.iv_cart)
        cart_btn.setOnClickListener{
            val intent = Intent(this,Rv_cart::class.java)
            intent.putExtra("previous_activity", "Category_Psu")
            startActivity(intent)
            finish()
        }

//        recyclerView = findViewById(R.id.category_psu_rv)
//        userAddressAdapter = PSU_Adapter(this, allitemlist)
//
//        recyclerView.layoutManager = GridLayoutManager(this, 2)
//        recyclerView.adapter = userAddressAdapter
//
//        my_Purchase_All_Data()
    }
    private fun navigateBack() {
        startActivity(Intent(this, Rv_Home_Page::class.java))
        finish()
    }

    // FOR API ===
    private fun getPSUS(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getPSUS()

        retrofitData.enqueue(object : Callback<List<ProductsItem>?> {
            override fun onResponse(
                call: Call<List<ProductsItem>?>,
                response: Response<List<ProductsItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter_Category_CPU = MyAdapter_Categories(baseContext, responseBody)
                myAdapter_Category_CPU.notifyDataSetChanged()
                category_psu_rv.adapter = myAdapter_Category_CPU
            }

            override fun onFailure(call: Call<List<ProductsItem>?>, t: Throwable) {
                Log.d("HomePage", "onFailure" + t.message)
            }
        })
    }
    // ===
//    private fun my_Purchase_All_Data() {
//        var items = PSU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = PSU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = PSU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = PSU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = PSU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = PSU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//
//
//    }
}