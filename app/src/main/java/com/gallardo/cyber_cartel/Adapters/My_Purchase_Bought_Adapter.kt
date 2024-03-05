//package com.gallardo.cyber_cartel.Adapters
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.cardview.widget.CardView
//import androidx.recyclerview.widget.RecyclerView
//import com.gallardo.cyber_cartel.DataClass.My_Purchase_Bought_DC
//import com.gallardo.cyber_cartel.My_Purchase_Bought
//import com.gallardo.cyber_cartel.R
//
//class My_Purchase_Bought_Adapter (private val getActivity: My_Purchase_Bought, private val receivedItemList: List<My_Purchase_Bought_DC>):
//    RecyclerView.Adapter<My_Purchase_Bought_Adapter.MyViewHolder>(){
//
//    override fun onCreateViewHolder(parent: ViewGroup, position: Int): My_Purchase_Bought_Adapter.MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_my_purchase_bought_items, parent, false)
//
//        return My_Purchase_Bought_Adapter.MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.tvProductInfo_Received.text =receivedItemList[position].Product_Info
//        holder.tvProductPrice_Received.text = receivedItemList[position].Product_Price.toString()
//        holder.ivProductImage_Received.setImageResource(receivedItemList[position].Product_Image)
//
//        holder.cardView_Received.setOnClickListener{
//            Toast.makeText(getActivity,receivedItemList[position].Product_Info, Toast.LENGTH_LONG).show()
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return receivedItemList.size
//    }
//
//    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val tvProductInfo_Received : TextView = itemView.findViewById(R.id.product_Info)
//        val tvProductPrice_Received : TextView = itemView.findViewById(R.id.product_price)
//        val ivProductImage_Received : ImageView = itemView.findViewById(R.id.product_image)
//        val cardView_Received : CardView = itemView.findViewById(R.id.my_purchase_bought_items)
//    }
//}