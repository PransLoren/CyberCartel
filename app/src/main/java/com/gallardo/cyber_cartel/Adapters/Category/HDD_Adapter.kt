package com.gallardo.cyber_cartel.Adapters.Category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.Category_Hdd
import com.gallardo.cyber_cartel.DataClass.Category.HDD_DC
import com.gallardo.cyber_cartel.R

class HDD_Adapter(private val getActivity: Category_Hdd, private val allItemList: ArrayList<HDD_DC>):
    RecyclerView.Adapter<HDD_Adapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HDD_Adapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_holder, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HDD_Adapter.MyViewHolder, position: Int) {
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
        val tvProductInfo : TextView = itemView.findViewById(R.id.tv_product_title)
        val tvProductPrice : TextView = itemView.findViewById(R.id.tv_product_title)
        val ivProductImage : ImageView = itemView.findViewById(R.id.iv_product_Image)
        val cardView : CardView = itemView.findViewById(R.id.product_cardView)

    }
}