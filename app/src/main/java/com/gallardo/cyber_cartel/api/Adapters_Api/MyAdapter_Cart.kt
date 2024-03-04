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
import com.gallardo.cyber_cartel.api.models.CartItem

class MyAdapter_Cart (val context: Context, val cartList: List<CartItem>): RecyclerView.Adapter<MyAdapter_Cart.ViewHolder>() {

    class ViewHolder(cartView: View): RecyclerView.ViewHolder(cartView){

        val item_image: ImageView = cartView.findViewById(R.id.item_image)

        val tv_item_model: TextView = cartView.findViewById(R.id.tv_item_model)
        val item_price: TextView = cartView.findViewById(R.id.item_price)
        val item_description: TextView = cartView.findViewById(R.id.item_description)
        val quantity_textView: TextView = cartView.findViewById(R.id.quantity_textView)

//        val delete_button: Button = cartView.findViewById(R.id.)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cartView = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false)
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

        holder.tv_item_model.text=currentItem.name
        holder.item_price.text=currentItem.price
        holder.item_description.text=currentItem.details
        holder.quantity_textView.text=currentItem.quantity
    }
}