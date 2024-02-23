package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.User_Addresses_Adapter
import com.gallardo.cyber_cartel.DataClass.User_Addresses_DC

class User_Addresses : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAddressAdapter: User_Addresses_Adapter
    private var address = ArrayList<User_Addresses_DC>()
    private lateinit var img_back : ImageView
    private lateinit var tv_new_address : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_addresses)

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

        recyclerView = findViewById(R.id.user_addresses_rv)
        userAddressAdapter = User_Addresses_Adapter(this, address)

        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = userAddressAdapter

        my_Purchase_All_Data()
    }

    private fun my_Purchase_All_Data() {
        val item = User_Addresses_DC(
            "Honrad O. Gallardo",
            9994172888,
            212,
            "Canave Street",
            "Magtaking",
            "Bugallon",
            "Pangasinan",
            2416) // Create a new instance of your data class

        address.add(item) // Add the item to the list
        userAddressAdapter.notifyDataSetChanged() // Notify the adapter that data has changed
    }
}
