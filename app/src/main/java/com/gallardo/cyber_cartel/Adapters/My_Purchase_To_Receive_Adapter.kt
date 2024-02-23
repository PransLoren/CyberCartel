package com.gallardo.cyber_cartel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.DataClass.My_Purchase_To_Receive_DC
import com.gallardo.cyber_cartel.My_Purchase_Refunded
import com.gallardo.cyber_cartel.R

class My_Purchase_To_Receive_Adapter (private val getActivity: My_Purchase_Refunded, private val toReceiveItemList: List<My_Purchase_To_Receive_DC>):
    RecyclerView.Adapter<My_Purchase_To_Receive_Adapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): My_Purchase_To_Receive_Adapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_purchase_item_holder, parent, false)

        return My_Purchase_To_Receive_Adapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvProductInfo_ToReceive.text =toReceiveItemList[position].Product_Info
        holder.tvProductPrice_ToReceive.text = toReceiveItemList[position].Product_Price.toString()
        holder.ivProductImage_ToReceive.setImageResource(toReceiveItemList[position].Product_Image)

        holder.cardView_ToReceive.setOnClickListener{
            Toast.makeText(getActivity,toReceiveItemList[position].Product_Info, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return toReceiveItemList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvProductInfo_ToReceive : TextView = itemView.findViewById(R.id.product_Info)
        val tvProductPrice_ToReceive : TextView = itemView.findViewById(R.id.product_price)
        val ivProductImage_ToReceive : ImageView = itemView.findViewById(R.id.product_image)
        val cardView_ToReceive : CardView = itemView.findViewById(R.id.my_purchase_product_holder)
    }
}