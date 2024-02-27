package com.gallardo.cyber_cartel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Checkout
import com.gallardo.cyber_cartel.DataClass.Checkout_DC
import com.gallardo.cyber_cartel.R

class Checkout_Adapter(private val getActivity: Checkout, private val checkOutList: List<Checkout_DC>):
    RecyclerView.Adapter<Checkout_Adapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Checkout_Adapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.checkout_item_holder, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: Checkout_Adapter.MyViewHolder, position: Int) {
        holder.tvProductPrice.text = checkOutList[position].Product_Price.toString()
        holder.tvProductInfo.text = checkOutList[position].Product_Name
        holder.tvProductQuantity.text = checkOutList[position].Product_Quantity.toString()
        holder.ivProductImage.setImageResource(checkOutList[position].Product_Image)

        holder.cardView.setOnClickListener{
            Toast.makeText(getActivity, checkOutList[position].Product_Name, Toast.LENGTH_LONG).show()
        }
    }
    override fun getItemCount(): Int {
        return checkOutList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvProductInfo : TextView = itemView.findViewById(R.id.tv_name_checkout)
        val tvProductPrice : TextView = itemView.findViewById(R.id.tv_price_checkout)
        val tvProductQuantity : TextView = itemView.findViewById(R.id.tv_quantity_checkout)
        val ivProductImage : ImageView = itemView.findViewById(R.id.iv_checkout)
        val cardView : CardView = itemView.findViewById(R.id.cardView_Checkout)

    }
}