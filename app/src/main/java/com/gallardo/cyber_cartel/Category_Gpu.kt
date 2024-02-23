package com.gallardo.cyber_cartel

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.Category.CPU_Adapter
import com.gallardo.cyber_cartel.Adapters.Category.GPU_Adapter
import com.gallardo.cyber_cartel.Adapters.User_Addresses_Adapter
import com.gallardo.cyber_cartel.DataClass.Category.GPU_DC

class Category_Gpu:AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAddressAdapter: GPU_Adapter
    private var allitemlist = ArrayList<GPU_DC>()
    private lateinit var img_back : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_gpu)

        recyclerView = findViewById(R.id.category_gpu_rv)
        userAddressAdapter = GPU_Adapter(this, allitemlist)

        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = userAddressAdapter

        my_Purchase_All_Data()
    }
    private fun my_Purchase_All_Data() {
        var items = GPU_DC("All Item", 100, R.drawable.image)
        allitemlist.add(items)
    }
}