package com.gallardo.cyber_cartel

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.Category.MoBo_Adapter
import com.gallardo.cyber_cartel.Adapters.User_Addresses_Adapter
import com.gallardo.cyber_cartel.DataClass.Category.MoBo_DC
import com.gallardo.cyber_cartel.DataClass.My_Purchase_All_DC
import com.gallardo.cyber_cartel.DataClass.User_Addresses_DC

class Category_MoBo: AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAddressAdapter: MoBo_Adapter
    private var allitemlist = ArrayList<MoBo_DC>()
    private lateinit var img_back : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_motherboard)

        recyclerView = findViewById(R.id.category_mobo_rv)
        userAddressAdapter = MoBo_Adapter(this, allitemlist)

        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = userAddressAdapter

        my_Purchase_All_Data()
    }
    private fun my_Purchase_All_Data() {
        var items = MoBo_DC("All Item", 100, R.drawable.image)
        allitemlist.add(items)
    }
}