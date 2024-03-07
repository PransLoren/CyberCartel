package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Cart
import com.gallardo.cyber_cartel.api.models.CartItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class Rv_cart : AppCompatActivity() {



    // API ===
    lateinit var myAdapter_Cart: MyAdapter_Cart
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var Cart_RecyclerView: RecyclerView
    //

//    private var recyclerView : RecyclerView? = null
//    private var cartAdapter : cart_adapter? = null
//    private var productList = mutableListOf<cart_dataclass>()

    private lateinit var BackArrow : ImageView
    private lateinit var checkOut : Button

    private lateinit var subTotal : TextView
    private lateinit var shipping : TextView
    private lateinit var total : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_cart)

        // API ===

        // TOKEN
        val authToken = intent.getStringExtra("authToken")

        Cart_RecyclerView = findViewById(R.id.Cart_RecyclerView)

        Cart_RecyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        Cart_RecyclerView.layoutManager = linearLayoutManager
        getMyCart()

        // ===

//        productList = ArrayList()

        BackArrow = findViewById(R.id.Cart_backButton)
        BackArrow.setOnClickListener {
            navigateBack()
        }



        // Cart Prices
        subTotal = findViewById(R.id.tv_total_price)
        subTotal.text = "150.00"


        //Checkout Button
        checkOut = findViewById(R.id.checkOut_Button)
        checkOut.setOnClickListener{
            // TOKEN
            intent.putExtra("authToken", authToken)

            val intent = Intent(this,Checkout::class.java)
            intent.putExtra("previous_activity", "Checkout")
            startActivity(intent)
            finish()
        }

//        recyclerView = findViewById<View>(R.id.Cart_RecyclerView) as RecyclerView
//        cartAdapter = cart_adapter(this@Rv_cart,productList)
//        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
//        recyclerView!!.layoutManager = layoutManager
////        cartRecyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView!!.adapter = cartAdapter
//
//        prepareProductListData()

    }

    // FOR API ===
    private fun getMyCart(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getCart()

        retrofitData.enqueue(object : Callback<List<CartItem>?> {
            override fun onResponse(
                call: Call<List<CartItem>?>,
                response: Response<List<CartItem>?>) {

                val responseBody = response.body()!!
                myAdapter_Cart = MyAdapter_Cart(baseContext, responseBody)
                myAdapter_Cart.notifyDataSetChanged()
                Cart_RecyclerView.adapter = myAdapter_Cart
            }

            override fun onFailure(call: Call<List<CartItem>?>, t: Throwable) {
                d("HomePage", "onFailure" + t.message)
            }
        })
    }
    // ===

    private fun navigateBack() {
        val previousActivity = intent.getStringExtra("previous_activity")
        when (previousActivity) {
            "Category_Cpu" -> {
                startActivity(Intent(this, Category_Cpu::class.java))
            }
            "Category_Gpu" -> {
                startActivity(Intent(this, Category_Gpu::class.java))
            }
            "Category_Hdd" -> {
                startActivity(Intent(this, Category_Hdd::class.java))
            }
            "Category_MoBo" -> {
                startActivity(Intent(this, Category_MoBo::class.java))
            }
            "Category_Ssd" -> {
                startActivity(Intent(this, Category_Case::class.java))
            }
            "Category_Ram" -> {
                startActivity(Intent(this, Category_Ram::class.java))
            }
            "Category_Psu" -> {
                startActivity(Intent(this, Category_Psu::class.java))
            }
            "Cart" -> {
                startActivity(Intent(this, Product_Page::class.java))
            }
            "My_Account" -> {
                startActivity(Intent(this, My_Account::class.java))
            }
            "My_Purchase_All" -> {
                startActivity(Intent(this, My_Purchase_All::class.java))
            }
            "My_Purchase_Bought" -> {
                startActivity(Intent(this, My_Purchase_Bought::class.java))
            }
            "My_Purchase_Cancelled" -> {
                startActivity(Intent(this, My_Purchase_Cancelled::class.java))
            }
            "My_Purchase_Refunded" -> {
                startActivity(Intent(this, My_Purchase_Refunded::class.java))
            }
            "Amd_Build" -> {
                startActivity(Intent(this, Rv_amd_build::class.java))
            }
            "Intel_Build" -> {
                startActivity(Intent(this, Rv_intel_build::class.java))
            }
            else -> {
                // Handle unknown previous activity or default behavior
                // For example, navigate back to the home screen
                startActivity(Intent(this, Rv_Home_Page::class.java))
            }
        }
        finish()
    }

//    private fun prepareProductListData() {
//        var product = cart_dataclass("AMD INTELCORE 15GEN PROCESSOR", "12,000","okay",R.drawable.cpu_product1,"1")
//        productList.add(product)
//        product = cart_dataclass("cpu", "12,000","okay",R.drawable.cpu_product1,"1")
//        productList.add(product)
//        product = cart_dataclass("cpu", "12,000","okay",R.drawable.cpu_product1,"1")
//        productList.add(product)
//
//    }
}