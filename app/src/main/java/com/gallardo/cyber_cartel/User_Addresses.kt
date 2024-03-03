package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.gallardo.cyber_cartel.Adapters.User_Addresses_Adapter
import com.gallardo.cyber_cartel.DataClass.User_Addresses_DC
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Address
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Categories
import com.gallardo.cyber_cartel.api.models.AddressItem
import com.gallardo.cyber_cartel.api.models.ProductsItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class User_Addresses : AppCompatActivity() {

    // API ===
    lateinit var myAdapter_Address: MyAdapter_Address
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var user_addresses_rv: RecyclerView
    //


    private lateinit var recyclerView: RecyclerView
//    private lateinit var userAddressAdapter: User_Addresses_Adapter
    private var address = ArrayList<User_Addresses_DC>()
    private lateinit var img_back : ImageView
    private lateinit var tv_new_address : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_addresses)

        // API ===
        user_addresses_rv = findViewById(R.id.user_addresses_rv)
        user_addresses_rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        user_addresses_rv.layoutManager = linearLayoutManager
        getAddress()
        // ===

        img_back = findViewById(R.id.back_to_profile)
        img_back.setOnClickListener{
            val intent = Intent(this,My_Account::class.java)
            startActivity(intent)
            finish()
        }

        tv_new_address = findViewById(R.id.add_new_address_tv)
        tv_new_address.setOnClickListener{
            val intent = Intent(this,New_Address::class.java)
            startActivity(intent)
            finish()
        }

//        recyclerView = findViewById(R.id.user_addresses_rv)
//        userAddressAdapter = User_Addresses_Adapter(this, address)
//
//        recyclerView.layoutManager = GridLayoutManager(this, 1)
//        recyclerView.adapter = userAddressAdapter

//        my_Purchase_All_Data()
    }

    // FOR API ===
    private fun getAddress(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getAddress()

        retrofitData.enqueue(object : Callback<List<AddressItem>?> {
            override fun onResponse(
                call: Call<List<AddressItem>?>,
                response: Response<List<AddressItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter_Address = MyAdapter_Address(baseContext, responseBody)
                myAdapter_Address.notifyDataSetChanged()
                user_addresses_rv.adapter = myAdapter_Address
            }

            override fun onFailure(call: Call<List<AddressItem>?>, t: Throwable) {
                Log.d("HomePage", "onFailure" + t.message)
            }
        })
    }
    // ===

//    private fun my_Purchase_All_Data() {
//        val item = User_Addresses_DC(
//            "Honrad O. Gallardo",
//            9994172888,
//            212,
//            "Canave Street",
//            "Magtaking",
//            "Bugallon",
//            "Pangasinan",
//            2416) // Create a new instance of your data class
//
//        address.add(item) // Add the item to the list
//        userAddressAdapter.notifyDataSetChanged() // Notify the adapter that data has changed
//    }
}
