package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.My_Purchase_All_Adapter
import com.gallardo.cyber_cartel.Adapters.My_Purchase_Cancelled_Adapter
import com.gallardo.cyber_cartel.DataClass.My_Purchase_All_DC
import com.gallardo.cyber_cartel.DataClass.My_Purchase_Cancelled_DC

class My_Purchase_Cancelled: AppCompatActivity() {
    private var recyclerView : RecyclerView? = null
    private var myPurchaseCancelledAdapter : My_Purchase_Cancelled_Adapter? = null
    private var cancelledItemList = mutableListOf<My_Purchase_Cancelled_DC>()
    private lateinit var tv_all : TextView
    private lateinit var tv_bought : TextView
    private lateinit var tv_refunded : TextView
    private lateinit var tv_cancelled : TextView
    private lateinit var img_bck : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_purchase_cancelled)


        tv_all = findViewById(R.id.all_tv)
        tv_all.setOnClickListener{
            val intent = Intent(this,My_Purchase_All::class.java)
            startActivity(intent)
            finish()
        }

        tv_bought = findViewById(R.id.bought_tv)
        tv_bought.setOnClickListener{
            val intent = Intent(this,My_Purchase_Bought::class.java)
            startActivity(intent)
            finish()
        }

        tv_cancelled = findViewById(R.id.cancelled_tv)
        tv_cancelled.setOnClickListener{
            val intent = Intent(this,My_Purchase_Cancelled::class.java)
            startActivity(intent)
            finish()
        }

        tv_refunded = findViewById(R.id.refunded_tv)
        tv_refunded.setOnClickListener{
            val intent = Intent(this,My_Purchase_Refunded::class.java)
            startActivity(intent)
            finish()
        }
        img_bck = findViewById(R.id.back_to_profile)
        img_bck.setOnClickListener{
            val intent = Intent(this,My_Account::class.java)
            startActivity(intent)
            finish()
        }


        cancelledItemList = ArrayList()

        recyclerView = findViewById<View>(R.id.my_purchase_cancelled_rv) as RecyclerView
        myPurchaseCancelledAdapter = My_Purchase_Cancelled_Adapter(this@My_Purchase_Cancelled,cancelledItemList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager (this,1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = myPurchaseCancelledAdapter

        my_Purchase_Cancelled_Data()
    }

    private fun my_Purchase_Cancelled_Data() {
        var items = My_Purchase_Cancelled_DC("All Item", 100, R.drawable.image)
        cancelledItemList.add(items)
    }
}