package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.Checkout_Adapter
import com.gallardo.cyber_cartel.DataClass.Checkout_DC
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Checkout
import com.gallardo.cyber_cartel.api.models.CartItem
import com.gallardo.cyber_cartel.api.models.`₱`
import com.gallardo.cyber_cartel.cb_api.ApiService
import com.gallardo.cyber_cartel.cb_api.SharedPreferencesManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Checkout: AppCompatActivity() {

    // API ===
    lateinit var myAdapter_Checkout: MyAdapter_Checkout
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var rv_checkout: RecyclerView


    // ===

    private lateinit var recyclerView: RecyclerView
    private lateinit var checkOutAdapter: Checkout_Adapter
    private var checkOutlist = ArrayList<Checkout_DC>()
    private lateinit var back_btn : ImageView
    private lateinit var confirm :  Button

    private lateinit var fullName : TextView
    private lateinit var phoneNumber : TextView
    private lateinit var houseNumber : TextView
    private lateinit var streetName : TextView
    private lateinit var barangay : TextView
    private lateinit var municipality : TextView
    private lateinit var province : TextView
    private lateinit var postalCode : TextView
    private lateinit var total : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout)


        // API ===

        rv_checkout = findViewById(R.id.rv_checkout)

        rv_checkout.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        rv_checkout.layoutManager = linearLayoutManager
        getMyCart()

        // ===
        val intent = intent

        val id = intent.getStringExtra("id")
        val address = intent.getStringExtra("address")
        val region = intent.getStringExtra("region")
        val city = intent.getStringExtra("city")
        val postal_code = intent.getStringExtra("postal_code")




        //Users Address
//        fullName = findViewById(R.id.tv_FullName_checkout)
//        fullName.text = "Full Name"


        houseNumber = findViewById(R.id.checkout_house_number)
        houseNumber.text = address

        municipality = findViewById(R.id.checkout_municipality)
        municipality.text = city

        province = findViewById(R.id.checkout_province)
        province.text = region

        postalCode = findViewById(R.id.tv_PostalCode_checkout)
        postalCode.text = postal_code


        //Total Price
        total = findViewById(R.id.tv_totalCart_checkout)
//        total.text = "100.00"


        back_btn = findViewById(R.id.Cart_backButton_checkout)
        back_btn.setOnClickListener(){
            val intent = Intent(this, Rv_cart::class.java)
            startActivity(intent)
        }
        confirm = findViewById(R.id.checkOut_Button_checkout)
        confirm.setOnClickListener(){
            val intent = Intent(this, My_Purchase_Bought::class.java)
            checkout()
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rv_checkout)
        checkOutAdapter = Checkout_Adapter(this,checkOutlist)

        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = checkOutAdapter

        getTotal()
//        checkOut_Data()
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
                    total.text = response.body().toString()
                }
                else{
                    total.text = "Failed to get total"
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
                response: Response<List<CartItem>?>
            ) {

                val responseBody = response.body()!!

                myAdapter_Checkout = MyAdapter_Checkout(baseContext, responseBody)
                myAdapter_Checkout.notifyDataSetChanged()
                rv_checkout.adapter = myAdapter_Checkout
            }

            override fun onFailure(call: Call<List<CartItem>?>, t: Throwable) {
                Log.d("HomePage", "onFailure" + t.message)
            }
        })
    }

    private fun checkout(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val accessToken = SharedPreferencesManager.getAccessToken(this)
        val retrofitData = retrofitBuilder.checkout(accessToken!!)

        retrofitData.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                Toast.makeText(
                    this@Checkout,
                    "Thank you for shopping at Cyber Cartel!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onFailure(call: Call<Void?>, t: Throwable) {
                //
            }
        })
    }

    // ===


//    private fun checkOut_Data() {
//        var items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
//        checkOutlist.add(items)
//        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
//        checkOutlist.add(items)
//        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
//        checkOutlist.add(items)
//        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
//        checkOutlist.add(items)
//        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
//        checkOutlist.add(items)
//        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
//        checkOutlist.add(items)
//
//
//
//    }

}