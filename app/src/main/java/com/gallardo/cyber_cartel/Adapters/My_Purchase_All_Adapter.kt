package com.gallardo.cyber_cartel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.DataClass.My_Purchase_All_DC
import com.gallardo.cyber_cartel.My_Purchase_All
import com.gallardo.cyber_cartel.R

class My_Purchase_All_Adapter(private val getActivity: My_Purchase_All, private val allItemList: List<My_Purchase_All_DC>):
    RecyclerView.Adapter<My_Purchase_All_Adapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): My_Purchase_All_Adapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_purchase_item_holder, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: My_Purchase_All_Adapter.MyViewHolder, position: Int) {
        holder.tvProductPrice.text = allItemList[position].Product_Price.toString()
        holder.tvProductInfo.text = allItemList[position].Product_Info
        holder.ivProductImage.setImageResource(allItemList[position].Product_Image)

        holder.cardView.setOnClickListener{
            Toast.makeText(getActivity, allItemList[position].Product_Info, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return allItemList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvProductInfo : TextView = itemView.findViewById(R.id.product_Info)
        val tvProductPrice : TextView = itemView.findViewById(R.id.product_price)
        val ivProductImage : ImageView = itemView.findViewById(R.id.product_image)
        val cardView : CardView = itemView.findViewById(R.id.my_purchase_product_holder)

    }
}