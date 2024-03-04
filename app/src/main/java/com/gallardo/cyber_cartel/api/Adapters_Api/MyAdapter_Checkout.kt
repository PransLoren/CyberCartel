package com.gallardo.cyber_cartel.api.Adapters_Api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gallardo.cyber_cartel.R
import com.gallardo.cyber_cartel.api.models.CartItem

class MyAdapter_Checkout (val context: Context, val checkoutList: List<CartItem>): RecyclerView.Adapter<MyAdapter_Checkout.ViewHolder>() {

    class ViewHolder(checkoutView: View): RecyclerView.ViewHolder(checkoutView){

        val item_image: ImageView = checkoutView.findViewById(R.id.iv_checkout)

        val tv_item_model: TextView = checkoutView.findViewById(R.id.tv_name_checkout)
        val item_price: TextView = checkoutView.findViewById(R.id.tv_price_checkout)
//        val item_description: TextView = checkoutView.findViewById(R.id.item_description)
        val quantity_textView: TextView = checkoutView.findViewById(R.id.tv_quantity_checkout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val checkoutView = LayoutInflater.from(context).inflate(R.layout.checkout_item_holder, parent, false)
        return ViewHolder(checkoutView)
    }

    override fun getItemCount(): Int {
        return checkoutList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = checkoutList[position]

        val imagename = currentItem.photo
        val imagepath = "https://fast-hollows-67866-0eecb0964658.herokuapp.com/$imagename"
        Glide.with(this.context).load(imagepath).into(holder.item_image)

        holder.tv_item_model.text=currentItem.name
        holder.item_price.text=currentItem.price
//        holder.item_description.text=currentItem.details
        holder.quantity_textView.text=currentItem.quantity
    }
}