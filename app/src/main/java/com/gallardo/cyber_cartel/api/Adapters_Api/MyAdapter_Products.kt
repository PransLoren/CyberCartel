package com.gallardo.cyber_cartel.api.Adapters_Api

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Product_Page
import com.gallardo.cyber_cartel.R
import com.gallardo.cyber_cartel.api.models.ProductsItem

class MyAdapter_Products(val context: Context, val productsList: List<ProductsItem>): RecyclerView.Adapter<MyAdapter_Products.ViewHolder>() {
    class ViewHolder(productView: View): RecyclerView.ViewHolder(productView){

        val tv_product_title: TextView = productView.findViewById(R.id.tv_product_title)
        val tv_product_price: TextView = productView.findViewById(R.id.tv_product_price)
        val tv_category_title: TextView = productView.findViewById(R.id.tv_category_title)

        val constraint_row : ConstraintLayout = productView.findViewById(R.id.constraint_row)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val productView = LayoutInflater.from(context).inflate(R.layout.product_holder, parent, false)
        return ViewHolder(productView)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productsList[position]

        holder.tv_product_title.text=currentItem.name
        holder.tv_category_title.text=currentItem.category
        holder.tv_product_price.text=currentItem.price

        val cont = holder.constraint_row.context
        holder.constraint_row.setOnClickListener{

            val intent = Intent(it.context, Product_Page::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

            intent.putExtra("name", currentItem.name)
            intent.putExtra("price", currentItem.price)
            intent.putExtra("category", currentItem.category)
            intent.putExtra("details", currentItem.details)

            it.context.startActivity(intent)
        }

    }


}