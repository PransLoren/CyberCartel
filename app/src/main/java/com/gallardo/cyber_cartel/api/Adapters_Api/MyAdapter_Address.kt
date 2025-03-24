package com.gallardo.cyber_cartel.api.Adapters_Api

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Checkout
import com.gallardo.cyber_cartel.Product_Page
import com.gallardo.cyber_cartel.R
import com.gallardo.cyber_cartel.api.models.AddressItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyAdapter_Address (val context: Context, val addressList: List<AddressItem>): RecyclerView.Adapter<MyAdapter_Address.ViewHolder>(){

    class ViewHolder(addressView: View): RecyclerView.ViewHolder(addressView){

        val address : TextView = addressView.findViewById(R.id.tv_House_Number)
        val province: TextView = addressView.findViewById(R.id.tv_Province)
        val city: TextView = addressView.findViewById(R.id.tv_Municipality)
        val postal: TextView = addressView.findViewById(R.id.tv_PostalCode)

        val delete_button: ImageView = addressView.findViewById(R.id.trashIcon_address)
        val select_button: TextView = addressView.findViewById(R.id.tv_SelectButton_address)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val addressView = LayoutInflater.from(context).inflate(R.layout.address_holder, parent, false)
        return ViewHolder(addressView)
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = addressList[position]

        holder.address.text=currentItem.address
        holder.province.text=currentItem.region
        holder.city.text=currentItem.city
        holder.postal.text=currentItem.postal_code

        holder.delete_button.setOnClickListener{
            deleteItem(currentItem.id)
        }

        holder.select_button.setOnClickListener{
            val intent = Intent(it.context, Checkout::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("id", currentItem.id)
            intent.putExtra("address", currentItem.address)
            intent.putExtra("region", currentItem.region)
            intent.putExtra("city", currentItem.city)
            intent.putExtra("postal_code", currentItem.postal_code)
            it.context.startActivity(intent)
        }

        // SELECT WORKS BUT DOESNT STAY
//        holder.select_button.setOnClickListener{
//            val intent = Intent(context, Checkout::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            context.startActivity(intent)
//
//            intent.putExtra("id", currentItem.id)
//            intent.putExtra("address", currentItem.address)
//            intent.putExtra("region", currentItem.region)
//            intent.putExtra("city", currentItem.city)
//            intent.putExtra("postal_code", currentItem.postal_code)
//            context.startActivity(intent)
//        }

    }


    fun deleteItem(id: Int){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.deleteAddress(id)

        retrofitData.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                //
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                //
            }
        })

    }

}