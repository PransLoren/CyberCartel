//package com.gallardo.cyber_cartel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gallardo.cyber_cartel.cb_api.Addresses
import com.gallardo.cyber_cartel.cb_api.RetrofitClient
import com.gallardo.cyber_cartel.cb_api.SharedPreferencesManager
import retrofit2.Callback
import retrofit2.Response

//lass New_Address: AppCompatActivity() {

   // //private lateinit var img_back : ImageView
   // private lateinit var btn_create_address : Button

  //  private lateinit var et_house_number : EditText
   /// private lateinit var et_Province : EditText
   // private lateinit var et_Region : EditText
   // private lateinit var et_PostalCode : EditText
   // override fun onCreate(savedInstanceState: Bundle?) {
      //  super.onCreate(savedInstanceState)
      //  setContentView(R.layout.add_address)

        // API
       /// et_house_number = findViewById(R.id.et_house_number)
       // et_Province = findViewById(R.id.et_Province)
      //  et_Region = findViewById(R.id.et_Region)
       // et_PostalCode = findViewById(R.id.et_PostalCode)

        //
       // img_back = findViewById(R.id.back_to_address)
       // img_back.setOnClickListener{
          //  val intent = Intent(this,User_Addresses::class.java)
           // startActivity(intent)
          //  finish()
     ///   }

      //  btn_create_address = findViewById(R.id.create_address_btn)
     //   btn_create_address.setOnClickListener{
//            val intent = Intent(this,User_Addresses::class.java)
       //     createAddress()
//            startActivity(intent)
//            finish()
       // }
  //  }
   // private fun createAddress(){
    //    val region = et_Region.text.toString().trim()
      //  val city = et_Province.text.toString().trim()
      //  val address = et_house_number.text.toString().trim()
       // val postal_code = et_PostalCode.text.toString().trim()

      //  if (region.isEmpty() || city.isEmpty() || address.isEmpty() || postal_code.isEmpty()) {
        //    Toast.makeText(
          //     this@New_Address,
         //       "Please fill in all fields",
          //      Toast.LENGTH_SHORT
        //    ).show()
         //   return
      //  }
       // val addresses = Addresses(
         //   region = region,
          //  city = city ,
           // address = address,
            //postal_code = postal_code
     //   )
        //val apiService = RetrofitClient.getService()

       // val accessToken = SharedPreferencesManager.getAccessToken(this)
        //val call = apiService.createAddress(accessToken!!,addresses)

       // call.enqueue(object : Callback<Addresses> {
           // override fun onResponse(call: retrofit2.Call<Addresses>, response: Response<Addresses>) {
               // if (response.isSuccessful) {
                 //   startActivity(Intent(this@New_Address, User_Addresses::class.java))
                  ///  finish()
                   // Toast.makeText(
                     //   this@New_Address,
                     //   "Created new address",
                      ///  Toast.LENGTH_SHORT

                    //).show()

              //  } else {
                   // Toast.makeText(
                     ///   this@New_Address,
                      ///  "Added Address",
                      //  Toast.LENGTH_SHORT
                  //  ).show()
               // }
         //   }

          //  override fun onFailure(call: retrofit2.Call<Addresses>, t: Throwable) {
       //         Toast.makeText(
         //           this@New_Address,
       //             "Added new Address",
            //        Toast.LENGTH_SHORT
          //      ).show()
         //   }
      //  })
 //   }

//    private fun createAddress(){
//        val region = et_Region.text.toString().trim()
//        val city = et_Province.text.toString().trim()
//        val address = et_house_number.text.toString().trim()
//        val postal_code = et_PostalCode.text.toString().trim()
//
//        if (region.isEmpty() || city.isEmpty() || address.isEmpty() || postal_code.isEmpty()) {
//            Toast.makeText(
//                this@New_Address,
//                "Please fill in all fields",
//                Toast.LENGTH_SHORT
//            ).show()
//            return
//        }
//        val addresses = Addresses(
//            region = region,
//            city = city ,
//            address = address,
//            postal_code = postal_code
//        )
//        val apiService = RetrofitClient.getService()
//        val call = apiService.createAddress(addresses)
//
//        call.enqueue(object : Callback<Addresses> {
//            override fun onResponse(call: retrofit2.Call<Addresses>, response: Response<Addresses>) {
//
//                if (response.isSuccessful) {
//                    startActivity(Intent(this@New_Address, User_Addresses::class.java))
//                    finish()
//                    Toast.makeText(
//                        this@New_Address,
//                        "Created new address",
//                        Toast.LENGTH_SHORT
//
//                    ).show()
//
//                } else {
//                    Toast.makeText(
//                        this@New_Address,
//                        "Failed",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
//                Toast.makeText(
//                    this@New_Address,
//                    "Already exists",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })
//    }
//}