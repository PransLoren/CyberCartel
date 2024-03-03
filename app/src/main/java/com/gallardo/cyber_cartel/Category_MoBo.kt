package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.Category.MoBo_Adapter
import com.gallardo.cyber_cartel.DataClass.Category.MoBo_DC
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Categories
import com.gallardo.cyber_cartel.api.models.ProductsItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Category_MoBo: AppCompatActivity() {

    // API ===
    lateinit var myAdapter_Category_CPU: MyAdapter_Categories
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var category_mobo_rv: RecyclerView
    //

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAddressAdapter: MoBo_Adapter
    private var allitemlist = ArrayList<MoBo_DC>()
    private lateinit var img_back : ImageView
    private lateinit var cart_btn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_motherboard)

        // API ===
        category_mobo_rv = findViewById(R.id.category_mobo_rv)
        category_mobo_rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        category_mobo_rv.layoutManager = linearLayoutManager
        getMotherboard()
        // ===

        img_back = findViewById(R.id.back_to_home)
        img_back.setOnClickListener{
            navigateBack()
        }

        cart_btn = findViewById(R.id.iv_cart)
        cart_btn.setOnClickListener{
            val intent = Intent(this,Rv_cart::class.java)
            intent.putExtra("previous_activity", "Category_MoBo")
            startActivity(intent)
            finish()
        }

//        recyclerView = findViewById(R.id.category_mobo_rv)
//        userAddressAdapter = MoBo_Adapter(this, allitemlist)
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
    private fun getMotherboard(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getMotherboards()

        retrofitData.enqueue(object : Callback<List<ProductsItem>?> {
            override fun onResponse(
                call: Call<List<ProductsItem>?>,
                response: Response<List<ProductsItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter_Category_CPU = MyAdapter_Categories(baseContext, responseBody)
                myAdapter_Category_CPU.notifyDataSetChanged()
                category_mobo_rv.adapter = myAdapter_Category_CPU
            }

            override fun onFailure(call: Call<List<ProductsItem>?>, t: Throwable) {
                Log.d("HomePage", "onFailure" + t.message)
            }
        })
    }
    // ===
//    private fun my_Purchase_All_Data() {
//        var items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
//        allitemlist.add(items)
//
//    }
}