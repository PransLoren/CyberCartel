package com.gallardo.cyber_cartel
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.My_Purchase_All_Adapter
import com.gallardo.cyber_cartel.DataClass.My_Purchase_All_DC
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Bought
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Cancelled
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_ProfileProducts
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Refunded
import com.gallardo.cyber_cartel.api.models.ProfileProductsItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class My_Purchase_All : AppCompatActivity() {

    // API ===
    lateinit var myAdapter_Bought: MyAdapter_Bought
    lateinit var myAdapter_Cancelled: MyAdapter_Cancelled
    lateinit var myAdapter_Refunded: MyAdapter_Refunded

    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var my_purchase_All_rv: RecyclerView
    //

    private var recyclerView : RecyclerView? = null
    private var myPurchaseAllAdapter : My_Purchase_All_Adapter? = null
    private var allitemlist = mutableListOf<My_Purchase_All_DC>()
    private lateinit var tv_all : TextView
    private lateinit var tv_bought : TextView
    private lateinit var tv_refunded : TextView
    private lateinit var tv_cancelled : TextView
    private lateinit var img_bck : ImageView
    private lateinit var cart : ImageView
    private lateinit var bottomNaviation : BottomNavigationView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_purchase_all)

        // API ===
        my_purchase_All_rv = findViewById(R.id.my_purchase_All_rv)

        my_purchase_All_rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        my_purchase_All_rv.layoutManager = linearLayoutManager


        getBought()
        getCancelled()
        getRefunded()
        // ===

        bottomNaviation = findViewById(R.id.btnav_bottomNavigation_MyProfile)

        bottomNaviation.setOnItemSelectedListener {
            when(it.itemId){
//                R.id.home -> {val intent = Intent(this, rvHompagee::class.java)
//                    startActivity(intent)
//                    finish()}

                R.id.home ->{val intent = Intent(this, Rv_Home_Page::class.java)
                    startActivity(intent)
                    finish()}

                R.id.me_profile -> {val  intent = Intent(this, My_Account::class.java)
                    startActivity(intent)
                    finish()}
            }
            true
        }

        cart = findViewById(R.id.Cart)
        cart.setOnClickListener(){
            val intent = Intent(this, Rv_cart::class.java)
            intent.putExtra("previous_activity", "My_Purchase_All")
            startActivity(intent)
        }

        tv_all = findViewById(R.id.all_tv)
        tv_all.setOnClickListener{
            val intent = Intent(this,My_Purchase_All::class.java)
            startActivity(intent)
            finish()
        }

        tv_bought = findViewById(R.id.bought_tv)
        tv_bought.setOnClickListener{
            val intent = Intent(this,My_Purchase_Bought::class.java)
            startActivity(intent)
            finish()
        }

        tv_cancelled = findViewById(R.id.cancelled_tv)
        tv_cancelled.setOnClickListener{
            val intent = Intent(this,My_Purchase_Cancelled::class.java)
            startActivity(intent)
            finish()
        }

        tv_refunded = findViewById(R.id.refunded_tv)
        tv_refunded.setOnClickListener{
            val intent = Intent(this,My_Purchase_Refunded::class.java)
            startActivity(intent)
            finish()
        }

        img_bck = findViewById(R.id.back_to_profile)
        img_bck.setOnClickListener{
            val intent = Intent(this,My_Account::class.java)
            startActivity(intent)
            finish()
        }



        allitemlist = ArrayList()

        recyclerView = findViewById<View>(R.id.my_purchase_All_rv) as RecyclerView
        myPurchaseAllAdapter = My_Purchase_All_Adapter(this@My_Purchase_All,allitemlist)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager (this,1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = myPurchaseAllAdapter

//        my_Purchase_All_Data()


    }

    // FOR API ===
    private fun getCancelled(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getCancelled()

        retrofitData.enqueue(object : Callback<List<ProfileProductsItem>?> {
            override fun onResponse(
                call: Call<List<ProfileProductsItem>?>,
                response: Response<List<ProfileProductsItem>?>) {

                val responseBody = response.body()!!
                myAdapter_Cancelled = MyAdapter_Cancelled(baseContext, responseBody)
                myAdapter_Cancelled.notifyDataSetChanged()
                my_purchase_All_rv.adapter = myAdapter_Cancelled
            }

            override fun onFailure(call: Call<List<ProfileProductsItem>?>, t: Throwable) {
                Log.d("HomePage", "onFailure" + t.message)
            }
        })
    }

    private fun getBought(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getBought()

        retrofitData.enqueue(object : Callback<List<ProfileProductsItem>?> {
            override fun onResponse(
                call: Call<List<ProfileProductsItem>?>,
                response: Response<List<ProfileProductsItem>?>
            ) {

                val responseBody = response.body()!!
                myAdapter_Bought = MyAdapter_Bought(baseContext, responseBody)
                myAdapter_Bought.notifyDataSetChanged()
                my_purchase_All_rv.adapter = myAdapter_Bought
            }

            override fun onFailure(call: Call<List<ProfileProductsItem>?>, t: Throwable) {
                Log.d("HomePage", "onFailure" + t.message)
            }
        })
    }
    private fun getRefunded(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getRefunded()

        retrofitData.enqueue(object : Callback<List<ProfileProductsItem>?> {
            override fun onResponse(
                call: Call<List<ProfileProductsItem>?>,
                response: Response<List<ProfileProductsItem>?>
            ) {

                val responseBody = response.body()!!
                myAdapter_Refunded = MyAdapter_Refunded(baseContext, responseBody)
                myAdapter_Refunded.notifyDataSetChanged()
                my_purchase_All_rv.adapter = myAdapter_Refunded
            }

            override fun onFailure(call: Call<List<ProfileProductsItem>?>, t: Throwable) {
                Log.d("HomePage", "onFailure" + t.message)
            }
        })
    }
    // ===

//    private fun my_Purchase_All_Data() {
//        var items = My_Purchase_All_DC("Item Name", 100, R.drawable.image)
//        allitemlist.add(items)
//        items = My_Purchase_All_DC("Item Name", 100, R.drawable.image)
//        allitemlist.add(items)
//        items = My_Purchase_All_DC("Item Name", 100, R.drawable.image)
//        allitemlist.add(items)
//
//    }
}
