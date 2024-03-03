package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class Product_Page : AppCompatActivity() {

    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productDescription: TextView

    private lateinit var img_back: ImageView
    private lateinit var product_cart: Button
    private lateinit var cartz: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)


        //Product Images
        val Product_viewPager: ViewPager2 = findViewById(R.id.product_viewPager2)

        //Add Images here:
        val images = listOf(
            R.drawable.image,
            R.drawable.image,
            R.drawable.image,)
        Product_viewPager.adapter = ViewPager_Adapter(images)

        img_back = findViewById(R.id.back_btn)
        img_back.setOnClickListener {
            navigateBack()
        }

        product_cart = findViewById(R.id.product_addToCart)
        product_cart.setOnClickListener {
            // Show a toast message
            Toast.makeText(this@Product_Page, "Product added to cart!", Toast.LENGTH_SHORT).show()
        }

        cartz = findViewById(R.id.Cart)
        cartz.setOnClickListener {
            val intent = Intent(this,Rv_cart::class.java)
            intent.putExtra("previous_activity", "Cart")
            startActivity(intent)
            finish()
        }


        //Product Name
        productName = findViewById(R.id.product_name)
        productName.text = "Product Name"

        //Product Price
        productPrice = findViewById(R.id.product_price)
        productPrice.text = "Product Price"

        //Product Description
        productDescription = findViewById(R.id.product_description)
        productDescription.text = "General Specs \n"
    }

    private fun navigateBack() {
        val previousActivity = intent.getStringExtra("previous_activity")
        when (previousActivity) {
            "Category_Cpu" -> {
                startActivity(Intent(this, Category_Cpu::class.java))
            }
            "Category_Gpu" -> {
                startActivity(Intent(this, Category_Gpu::class.java))
            }
            "Category_Hdd" -> {
                startActivity(Intent(this, Category_Hdd::class.java))
            }
            "Category_MoBo" -> {
                startActivity(Intent(this, Category_MoBo::class.java))
            }
            "Category_Ssd" -> {
                startActivity(Intent(this, Category_Case::class.java))
            }
            "Category_Ram" -> {
                startActivity(Intent(this, Category_Ram::class.java))
            }
            "Category_Psu" -> {
                startActivity(Intent(this, Category_Psu::class.java))
            }
            "Amd_Build" -> {
                startActivity(Intent(this, Rv_amd_build::class.java))
            }
            "Intel_Build" -> {
                startActivity(Intent(this, Rv_intel_build::class.java))
            }
            else -> {
                // Handle unknown previous activity or default behavior
                // For example, navigate back to the home screen
                startActivity(Intent(this, Rv_Home_Page::class.java))
            }
        }
        finish()
    }
}
