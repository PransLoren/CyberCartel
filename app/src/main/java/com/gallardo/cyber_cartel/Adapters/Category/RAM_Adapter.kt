package com.gallardo.cyber_cartel.Adapters.Category

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Adapters.My_Purchase_All_Adapter
import com.gallardo.cyber_cartel.Category_Ram
import com.gallardo.cyber_cartel.DataClass.Category.RAM_DC
import com.gallardo.cyber_cartel.DataClass.My_Purchase_All_DC
import com.gallardo.cyber_cartel.My_Purchase_All
import com.gallardo.cyber_cartel.R

class RAM_Adapter (private val getActivity: Category_Ram, private val ItemList: List<RAM_DC>):
    RecyclerView.Adapter<RAM_Adapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RAM_Adapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_holder, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RAM_Adapter.MyViewHolder, position: Int) {
        holder.tvProductPrice.text = ItemList[position].Product_Price.toString()
        holder.tvProductInfo.text = ItemList[position].Product_Info
        holder.ivProductImage.setImageResource(ItemList[position].Product_Image)

        holder.cardView.setOnClickListener{
            val selected = ItemList[position]

            val intent = Intent(getActivity, selected.targetActivity)
            intent.putExtra("previous_activity", "Category_Ram")
            getActivity.startActivity(intent)
            getActivity.finish()
        }
    }

    override fun getItemCount(): Int {
        return ItemList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvProductInfo : TextView = itemView.findViewById(R.id.tv_product_title)
        val tvProductPrice : TextView = itemView.findViewById(R.id.tv_product_title)
        val ivProductImage : ImageView = itemView.findViewById(R.id.iv_product_Image)
        val cardView : CardView = itemView.findViewById(R.id.product_cardView)

    }
}