package com.gallardo.cyber_cartel.api.Adapters_Api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gallardo.cyber_cartel.BASE_URL
import com.gallardo.cyber_cartel.R
import com.gallardo.cyber_cartel.api.models.CartItem
import com.gallardo.cyber_cartel.cb_api.ApiService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyAdapter_Cart (val context: Context, val cartList: List<CartItem>): RecyclerView.Adapter<MyAdapter_Cart.ViewHolder>() {

    class ViewHolder(cartView: View) : RecyclerView.ViewHolder(cartView) {

        val item_image: ImageView = cartView.findViewById(R.id.item_image)

        val tv_item_model: TextView = cartView.findViewById(R.id.tv_item_model)
        val item_price: TextView = cartView.findViewById(R.id.item_price)
        val item_description: TextView = cartView.findViewById(R.id.item_description)
        val quantity_textView: TextView = cartView.findViewById(R.id.quantity_textView)

        val delete_button: ImageView = cartView.findViewById(R.id.trashIcon)
        val increase_button: ImageView = cartView.findViewById(R.id.plus_icon)
        val decrease_button: ImageView = cartView.findViewById(R.id.minus_icon)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cartView =
            LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false)
        return ViewHolder(cartView)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cartList[position]

        val imagename = currentItem.photo
        val imagepath = "https://fast-hollows-67866-0eecb0964658.herokuapp.com/$imagename"
        Glide.with(this.context).load(imagepath).into(holder.item_image)

        holder.tv_item_model.text = currentItem.name
        holder.item_price.text = currentItem.price
//        holder.item_description.text = currentItem.details
        holder.quantity_textView.text = currentItem.quantity


        holder.delete_button.setOnClickListener{
            deleteItem(currentItem.id)
        }

        holder.increase_button.setOnClickListener{
            increaseItem(currentItem.id)
        }

        holder.decrease_button.setOnClickListener{
            decreaseItem(currentItem.id)
        }

    }
    fun decreaseItem(id:Int){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)


        val retrofitData = retrofitBuilder.decreaseItem(id)

        retrofitData.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                //
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                //
            }
        })
    }

    fun increaseItem(id:Int){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)


        val retrofitData = retrofitBuilder.increaseItem(id)

        retrofitData.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                //
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                //
            }
        })
    }

    fun deleteItem(id: Int){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.removeItem(id)

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


