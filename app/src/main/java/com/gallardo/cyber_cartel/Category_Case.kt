package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.Category.SSD_Adapter
import com.gallardo.cyber_cartel.DataClass.Category.SSD_DC
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Categories
import com.gallardo.cyber_cartel.api.models.ProductsItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Category_Case: AppCompatActivity() {

    // API ===
    lateinit var myAdapter_Category_CPU: MyAdapter_Categories
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var category_cpu_rv: RecyclerView
    //

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAddressAdapter: SSD_Adapter
    private var allitemlist = ArrayList<SSD_DC>()
    private lateinit var img_back : ImageView
    private lateinit var cart_btn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_ssd)

        // API ===
        category_cpu_rv = findViewById(R.id.category_ssd_rv)
        category_cpu_rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        category_cpu_rv.layoutManager = linearLayoutManager
        getCase()
        // ===


        img_back = findViewById(R.id.back_to_home)
        img_back.setOnClickListener{
            navigateBack()
        }

        cart_btn = findViewById(R.id.iv_cart)
        cart_btn.setOnClickListener{
            val intent = Intent(this,Rv_cart::class.java)
            intent.putExtra("previous_activity", "Category_Ssd")
            startActivity(intent)
            finish()
        }

//        recyclerView = findViewById(R.id.category_ssd_rv)
//        userAddressAdapter = SSD_Adapter(this, allitemlist)
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
    private fun getCase(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getCase()

        retrofitData.enqueue(object : Callback<List<ProductsItem>?> {
            override fun onResponse(
                call: Call<List<ProductsItem>?>,
                response: Response<List<ProductsItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter_Category_CPU = MyAdapter_Categories(baseContext, responseBody)
                myAdapter_Category_CPU.notifyDataSetChanged()
                category_cpu_rv.adapter = myAdapter_Category_CPU
            }

            override fun onFailure(call: Call<List<ProductsItem>?>, t: Throwable) {
                Log.d("HomePage", "onFailure" + t.message)
            }
        })
    }
    // ===

//    private fun my_Purchase_All_Data() {
//        var items = SSD_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = SSD_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = SSD_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = SSD_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = SSD_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = SSD_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//
//
//
//    }
}