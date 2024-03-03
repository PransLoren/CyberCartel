package com.gallardo.cyber_cartel.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.R
import com.gallardo.cyber_cartel.api.models.ProfileProductsItem

class MyAdapter_Bought (val context: Context, val cancelledList: List<ProfileProductsItem>): RecyclerView.Adapter<MyAdapter_Bought.ViewHolder>(){

    class ViewHolder(cancelledView: View): RecyclerView.ViewHolder(cancelledView){

        val product_Info: TextView = cancelledView.findViewById(R.id.product_Info)
        val product_price: TextView = cancelledView.findViewById(R.id.product_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cancelledView = LayoutInflater.from(context).inflate(R.layout.my_purchase_item_holder, parent, false)
        return ViewHolder(cancelledView)
    }

    override fun getItemCount(): Int {
        return cancelledList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cancelledList[position]

        holder.product_Info.text=currentItem.name
        holder.product_price.text=currentItem.price

    }

}