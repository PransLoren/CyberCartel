package com.gallardo.cyber_cartel
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.My_Purchase_All_Adapter
import com.gallardo.cyber_cartel.DataClass.My_Purchase_All_DC
import com.google.android.material.bottomnavigation.BottomNavigationView

class My_Purchase_All : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var myPurchaseAllAdapter : My_Purchase_All_Adapter? = null
    private var allitemlist = mutableListOf<My_Purchase_All_DC>()
    private lateinit var tv_all : TextView
    private lateinit var tv_bought : TextView
    private lateinit var tv_refunded : TextView
    private lateinit var tv_cancelled : TextView
    private lateinit var img_bck : ImageView
    private lateinit var cart : ImageView
    private lateinit var bottomNaviation : BottomNavigationView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_purchase_all)

        bottomNaviation = findViewById(R.id.btnav_bottomNavigation_MyProfile)

        bottomNaviation.setOnItemSelectedListener {
            when(it.itemId){
//                R.id.home -> {val intent = Intent(this, rvHompagee::class.java)
//                    startActivity(intent)
//                    finish()}

                R.id.home ->{val intent = Intent(this, Rv_Home_Page::class.java)
                    startActivity(intent)
                    finish()}

                R.id.pre_built ->{val intent = Intent(this, Rv_amd_build::class.java)
                    startActivity(intent)
                    finish()}

                R.id.me_profile -> {val  intent = Intent(this, My_Account::class.java)
                    startActivity(intent)
                    finish()}
            }
            true
        }

        cart = findViewById(R.id.Cart)
        cart.setOnClickListener(){
            val intent = Intent(this, Rv_cart::class.java)
            startActivity(intent)
        }

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



        allitemlist = ArrayList()

        recyclerView = findViewById<View>(R.id.my_purchase_All_rv) as RecyclerView
        myPurchaseAllAdapter = My_Purchase_All_Adapter(this@My_Purchase_All,allitemlist)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager (this,1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = myPurchaseAllAdapter

        my_Purchase_All_Data()


    }

    private fun my_Purchase_All_Data() {
        var items = My_Purchase_All_DC("All Item", 100, R.drawable.image)
        allitemlist.add(items)
    }
}
