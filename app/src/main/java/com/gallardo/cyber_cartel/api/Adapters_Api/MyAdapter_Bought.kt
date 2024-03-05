package com.gallardo.cyber_cartel.api.Adapters_Api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gallardo.cyber_cartel.R
import com.gallardo.cyber_cartel.api.models.ProfileProductsItem

class MyAdapter_Bought (val context: Context, val cancelledList: List<ProfileProductsItem>): RecyclerView.Adapter<MyAdapter_Bought.ViewHolder>(){

    class ViewHolder(cancelledView: View): RecyclerView.ViewHolder(cancelledView){

        val item_image: ImageView = cancelledView.findViewById(R.id.product_image)

        val product_Info: TextView = cancelledView.findViewById(R.id.product_Info)
        val product_price: TextView = cancelledView.findViewById(R.id.product_price)

        val cancel_button: Button = cancelledView.findViewById(R.id.button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cancelledView = LayoutInflater.from(context).inflate(R.layout.layout_my_purchase_bought_items, parent, false)
        return ViewHolder(cancelledView)
    }

    override fun getItemCount(): Int {
        return cancelledList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cancelledList[position]

        val imagename = currentItem.photo
        val imagepath = "https://fast-hollows-67866-0eecb0964658.herokuapp.com/$imagename"
        Glide.with(this.context).load(imagepath).into(holder.item_image)

        holder.product_Info.text=currentItem.name
        holder.product_price.text=currentItem.price

//        holder.cancel_button.setOnClickListener{
//
//        }

    }

}