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
import com.gallardo.cyber_cartel.api.models.`₱`
import com.gallardo.cyber_cartel.cb_api.ApiService
import com.gallardo.cyber_cartel.cb_api.SharedPreferencesManager
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
        Cart_RecyclerView = findViewById(R.id.Cart_RecyclerView)
        Cart_RecyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        Cart_RecyclerView.layoutManager = linearLayoutManager
        getMyCart()

        // DATA TRANSFER
        val id = intent.getIntExtra("id", 0)
        val photo = intent.getStringExtra("photo")
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val details = intent.getStringExtra("details")
        val category = intent.getStringExtra("category")
        // ===

//        productList = ArrayList()

        // PABALIK SA PRODUCT
        BackArrow = findViewById(R.id.Cart_backButton)
        BackArrow.setOnClickListener {
            val intent = Intent(this, Rv_Home_Page::class.java)

            intent.putExtra("id", id)
            intent.putExtra("name", name)
            intent.putExtra("price", price)
            intent.putExtra("category", category)
            intent.putExtra("details", details)
            intent.putExtra("photo", photo)
            startActivity(intent)
            finish()
//            startActivity(intent)

            navigateBack()
        }

        // YUNG TOTAL
        // Cart Prices
        subTotal = findViewById(R.id.total)
//        subTotal.text = ""
        getTotal()


        //Checkout Button
        checkOut = findViewById(R.id.checkOut_Button)
        checkOut.setOnClickListener{
            val intent = Intent(this,Checkout::class.java)
            intent.putExtra("previous_activity", "Checkout")
            startActivity(intent)
            finish()
        }

    }
    // FOR API ===
    private fun getTotal(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val accessToken = SharedPreferencesManager.getAccessToken(this)
        val retrofitData = retrofitBuilder.getCartSum(accessToken!!)

        retrofitData.enqueue(object : Callback<`₱`?> {
            override fun onResponse(call: Call<`₱`?>, response: Response<`₱`?>) {
                if(response.isSuccessful){
//                    val data = response.body()
                    subTotal.text = response.body().toString()
                }
                else{
                    subTotal.text = "Failed to get total"
                }
            }

            override fun onFailure(call: Call<`₱`?>, t: Throwable) {
                //
            }
        })
    }
    private fun getMyCart(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val accessToken = SharedPreferencesManager.getAccessToken(this)
        val retrofitData = retrofitBuilder.getCart(accessToken!!)

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
    // === //

    private fun navigateBack() {
        val id = intent.getIntExtra("id", 0)
        val photo = intent.getStringExtra("photo")
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val details = intent.getStringExtra("details")
        val category = intent.getStringExtra("category")

        intent.putExtra("id", id)
        intent.putExtra("name", name)
        intent.putExtra("price", price)
        intent.putExtra("category", category)
        intent.putExtra("details", details)
        intent.putExtra("photo", photo)

        val authToken = intent.getStringExtra("authToken")
        val previousActivity = intent.getStringExtra("previous_activity")
        when (previousActivity) {
            "Category_Cpu" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Category_Cpu::class.java))
            }
            "Category_Gpu" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Category_Gpu::class.java))
            }
            "Category_Hdd" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Category_Hdd::class.java))
            }
            "Category_MoBo" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Category_MoBo::class.java))
            }
            "Category_Ssd" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Category_Case::class.java))
            }
            "Category_Ram" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Category_Ram::class.java))
            }
            "Category_Psu" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Category_Psu::class.java))
            }
            "Cart" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Product_Page::class.java))
            }
            "My_Account" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, My_Account::class.java))
            }
            "My_Purchase_All" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, My_Purchase_All::class.java))
            }
            "My_Purchase_Bought" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, My_Purchase_Bought::class.java))
            }
            "My_Purchase_Cancelled" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, My_Purchase_Cancelled::class.java))
            }
            "My_Purchase_Refunded" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, My_Purchase_Refunded::class.java))
            }
            "Amd_Build" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Rv_amd_build::class.java))
            }
            "Intel_Build" -> {
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Rv_intel_build::class.java))
            }
            else -> {
                // Handle unknown previous activity or default behavior
                // For example, navigate back to the home screen
                intent.putExtra("authToken", authToken)
                startActivity(Intent(this, Rv_Home_Page::class.java))
            }
        }
        finish()
    }
}