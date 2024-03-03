package com.gallardo.cyber_cartel.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.R
import com.gallardo.cyber_cartel.api.models.CartItem

class MyAdapter_Checkout (val context: Context, val checkoutList: List<CartItem>): RecyclerView.Adapter<MyAdapter_Checkout.ViewHolder>() {

    class ViewHolder(checkoutView: View): RecyclerView.ViewHolder(checkoutView){

        val tv_item_model: TextView = checkoutView.findViewById(R.id.tv_item_model)
        val item_price: TextView = checkoutView.findViewById(R.id.item_price)
        val item_description: TextView = checkoutView.findViewById(R.id.item_description)
        val quantity_textView: TextView = checkoutView.findViewById(R.id.quantity_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val checkoutView = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false)
        return ViewHolder(checkoutView)
    }

    override fun getItemCount(): Int {
        return checkoutList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = checkoutList[position]

        holder.tv_item_model.text=currentItem.name
        holder.item_price.text=currentItem.price
        holder.item_description.text=currentItem.details
        holder.quantity_textView.text=currentItem.quantity
    }
}