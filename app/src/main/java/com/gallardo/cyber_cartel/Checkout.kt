package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.Category.CPU_Adapter
import com.gallardo.cyber_cartel.Adapters.Category.PSU_Adapter
import com.gallardo.cyber_cartel.Adapters.Checkout_Adapter
import com.gallardo.cyber_cartel.DataClass.Category.CPU_DC
import com.gallardo.cyber_cartel.DataClass.Category.SSD_DC
import com.gallardo.cyber_cartel.DataClass.Checkout_DC

class Checkout: AppCompatActivity() {
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



        //Users Address
        fullName = findViewById(R.id.tv_FullName_checkout)
        fullName.text = "Full Name"

        phoneNumber = findViewById(R.id.tv_PhoneNumber_checkout)
        phoneNumber.text = "Phone Number"

        houseNumber = findViewById(R.id.checkout_house_number)
        houseNumber.text = "House Number"

        streetName = findViewById(R.id.checkout_street_name)
        streetName.text = "Street Name"

        barangay = findViewById(R.id.checkout_barangay)
        barangay.text = "Barangay"

        municipality = findViewById(R.id.checkout_municipality)
        municipality.text = "Municipality"

        province = findViewById(R.id.checkout_province)
        province.text = "Province"

        postalCode = findViewById(R.id.tv_PostalCode_checkout)
        postalCode.text = "Postal Code"


        //Total Price
        total = findViewById(R.id.amount_Checkout)
        total.text = "100.00"


        back_btn = findViewById(R.id.Cart_backButton_checkout)
        back_btn.setOnClickListener(){
            val intent = Intent(this, Rv_cart::class.java)
            startActivity(intent)
        }
        confirm = findViewById(R.id.checkOut_Button_checkout)
        confirm.setOnClickListener(){
            val intent = Intent(this, My_Purchase_Bought::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rv_checkout)
        checkOutAdapter = Checkout_Adapter(this,checkOutlist)

        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = checkOutAdapter

        checkOut_Data()
    }

    private fun checkOut_Data() {
        var items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
        checkOutlist.add(items)
        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
        checkOutlist.add(items)
        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
        checkOutlist.add(items)
        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
        checkOutlist.add(items)
        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
        checkOutlist.add(items)
        items = Checkout_DC("All Item", 100.00, R.drawable.image, 1)
        checkOutlist.add(items)



    }

}