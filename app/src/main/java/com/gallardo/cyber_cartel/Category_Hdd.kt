package com.gallardo.cyber_cartel

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.Category.HDD_Adapter
import com.gallardo.cyber_cartel.DataClass.Category.HDD_DC

class Category_Hdd:AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAddressAdapter: HDD_Adapter
    private var allitemlist = ArrayList<HDD_DC>()
    private lateinit var img_back : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_hdd)

        recyclerView = findViewById(R.id.category_hdd_rv)
        userAddressAdapter = HDD_Adapter(this, allitemlist)

        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = userAddressAdapter

        my_Purchase_All_Data()
    }
    private fun my_Purchase_All_Data() {
        var items = HDD_DC("All Item", 100, R.drawable.image)
        allitemlist.add(items)
    }
}