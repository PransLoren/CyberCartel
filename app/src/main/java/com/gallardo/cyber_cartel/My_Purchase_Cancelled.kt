package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.My_Purchase_Cancelled_Adapter
import com.gallardo.cyber_cartel.DataClass.My_Purchase_Cancelled_DC
import com.gallardo.cyber_cartel.api.MyAdapter_Cancelled
import com.gallardo.cyber_cartel.api.models.ProfileProductsItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class My_Purchase_Cancelled: AppCompatActivity() {

    // API ===
    lateinit var myAdapter_Cancelled: MyAdapter_Cancelled
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var my_purchase_cancelled_rv: RecyclerView
    //

    private var recyclerView : RecyclerView? = null
    private var myPurchaseCancelledAdapter : My_Purchase_Cancelled_Adapter? = null
    private var cancelledItemList = mutableListOf<My_Purchase_Cancelled_DC>()
    private lateinit var tv_all : TextView
    private lateinit var tv_bought : TextView
    private lateinit var tv_refunded : TextView
    private lateinit var tv_cancelled : TextView
    private lateinit var img_bck : ImageView
    private lateinit var cart : ImageView
    private lateinit var bottomNaviation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_purchase_cancelled)

        // API ===

        my_purchase_cancelled_rv = findViewById(R.id.my_purchase_cancelled_rv)

        my_purchase_cancelled_rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        my_purchase_cancelled_rv.layoutManager = linearLayoutManager
        getCancelled()

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
            intent.putExtra("previous_activity", "My_Purchase_Cancelled")
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


        cancelledItemList = ArrayList()

        recyclerView = findViewById<View>(R.id.my_purchase_cancelled_rv) as RecyclerView
        myPurchaseCancelledAdapter = My_Purchase_Cancelled_Adapter(this@My_Purchase_Cancelled,cancelledItemList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager (this,1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = myPurchaseCancelledAdapter

        my_Purchase_Cancelled_Data()
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
                my_purchase_cancelled_rv.adapter = myAdapter_Cancelled
            }

            override fun onFailure(call: Call<List<ProfileProductsItem>?>, t: Throwable) {
                d("HomePage", "onFailure" + t.message)
            }
        })
    }
    // ===

    private fun my_Purchase_Cancelled_Data() {
        var items = My_Purchase_Cancelled_DC("All Item", 100, R.drawable.image)
        cancelledItemList.add(items)
    }
}