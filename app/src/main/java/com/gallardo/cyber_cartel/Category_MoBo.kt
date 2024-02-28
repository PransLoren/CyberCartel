package com.gallardo.cyber_cartel

import android.content.Intent
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
    private lateinit var cart_btn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_motherboard)

        img_back = findViewById(R.id.back_to_home)
        img_back.setOnClickListener{
            navigateBack()
        }

        cart_btn = findViewById(R.id.iv_cart)
        cart_btn.setOnClickListener{
            val intent = Intent(this,Rv_cart::class.java)
            intent.putExtra("previous_activity", "Category_MoBo")
            startActivity(intent)
            finish()
        }

        recyclerView = findViewById(R.id.category_mobo_rv)
        userAddressAdapter = MoBo_Adapter(this, allitemlist)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = userAddressAdapter

        my_Purchase_All_Data()
    }
    private fun navigateBack() {
        startActivity(Intent(this, Rv_Home_Page::class.java))
        finish()
    }
    private fun my_Purchase_All_Data() {
        var items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = MoBo_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)

    }
}