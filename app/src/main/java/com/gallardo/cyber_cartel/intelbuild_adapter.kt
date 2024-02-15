package com.gallardo.cyber_cartel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class intelbuild_adapter constructor(private val getActivity: Rv_intel_build, private val productList : List<intelbuild_dataclass>) :
    RecyclerView.Adapter<intelbuild_adapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_homepage_item, parent, false)

        return intelbuild_adapter.MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.productTitle.text = productList[position].productitle
        holder.productImg.setImageResource(productList[position].productimg)
        holder.productPrice.text = productList[position].productprice
        holder.cardView.setOnClickListener{
            Toast.makeText(getActivity, productList[position].productitle, Toast.LENGTH_LONG).show()
        }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val productTitle : TextView = itemView.findViewById(R.id.tv_title)
        val productImg : ImageView = itemView.findViewById(R.id.productImage)
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val productsold : TextView = itemView.findViewById(R.id.Cpu_Sold)
        val productPrice : TextView = itemView.findViewById(R.id.tv_price)


    }
}