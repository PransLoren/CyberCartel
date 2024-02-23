package com.gallardo.cyber_cartel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.DataClass.My_Purchase_Cancelled_DC
import com.gallardo.cyber_cartel.My_Purchase_Cancelled
import com.gallardo.cyber_cartel.R
import java.text.FieldPosition

class My_Purchase_Cancelled_Adapter(private val getActivity: My_Purchase_Cancelled, private val cancelledItemList: List<My_Purchase_Cancelled_DC>):
    RecyclerView.Adapter<My_Purchase_Cancelled_Adapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): My_Purchase_Cancelled_Adapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_purchase_item_holder, parent, false)

        return My_Purchase_Cancelled_Adapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvProductInfo_Cancelled.text =cancelledItemList[position].Product_Info
        holder.tvProductPrice_Cancelled.text = cancelledItemList[position].Product_Price.toString()
        holder.ivProductImage_Cancelled.setImageResource(cancelledItemList[position].Product_Image)

        holder.cardView_Cancelled.setOnClickListener{
            Toast.makeText(getActivity,cancelledItemList[position].Product_Info, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return cancelledItemList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvProductInfo_Cancelled : TextView = itemView.findViewById(R.id.product_Info)
        val tvProductPrice_Cancelled : TextView = itemView.findViewById(R.id.product_price)
        val ivProductImage_Cancelled : ImageView = itemView.findViewById(R.id.product_image)
        val cardView_Cancelled : CardView = itemView.findViewById(R.id.my_purchase_product_holder)
    }
}