package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.Category.CPU_Adapter
import com.gallardo.cyber_cartel.Adapters.User_Addresses_Adapter
import com.gallardo.cyber_cartel.DataClass.Category.CPU_DC

class Category_Cpu:AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cpuAdapter: CPU_Adapter
    private var allitemlist = ArrayList<CPU_DC>()
    private lateinit var img_back : ImageView
    private lateinit var cart_btn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_cpu)

        img_back = findViewById(R.id.back_to_home)
        img_back.setOnClickListener {
            navigateBack()
        }

        cart_btn = findViewById(R.id.iv_cart)
        cart_btn.setOnClickListener{
            val intent = Intent(this,Rv_cart::class.java)
            intent.putExtra("previous_activity", "Category_Cpu")
            startActivity(intent)
            finish()
        }

        recyclerView = findViewById(R.id.category_cpu_rv)
        cpuAdapter = CPU_Adapter(this, allitemlist)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter =cpuAdapter


        cpu_Product_Data()
    }
    private fun navigateBack() {
        startActivity(Intent(this, Rv_Home_Page::class.java))
        finish()
    }

    private fun cpu_Product_Data() {
        var items = CPU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = CPU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = CPU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = CPU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = CPU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
        items = CPU_DC("All Item", 100, R.drawable.image, Product_Page::class.java)
        allitemlist.add(items)
    }
}