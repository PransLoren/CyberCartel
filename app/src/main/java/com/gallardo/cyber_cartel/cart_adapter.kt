//package com.gallardo.cyber_cartel
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class cart_adapter constructor(private val getActivity: Rv_cart, private val productList: List<cart_dataclass>):
//    RecyclerView.Adapter<cart_adapter.MyViewHolder>(){
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return productList.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.itemModel.text = productList[position].productName
//        holder.itemDescription.text = productList[position].description
//        holder.itemPrice.text = productList[position].price.toString()
//        holder.itemImage.setImageResource(productList[position].imageResource)
//        holder.quantityNumber.text = productList[position].quantity.toString()
//    }
//
//    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
//        val itemModel : TextView = itemView.findViewById(R.id.tv_item_model)
//        val itemDescription : TextView = itemView.findViewById(R.id.item_description)
//        val itemPrice : TextView = itemView.findViewById(R.id.item_price)
//        val itemImage : ImageView = itemView.findViewById(R.id.item_image)
////        val trashIcon : ImageView = itemView.findViewById(R.id.trashIcon)
////        val minusIcon : ImageView = itemView.findViewById(R.id.minus_icon)
////        val plusIcon : ImageView = itemView.findViewById(R.id.plus_icon)
//        val quantityNumber : TextView = itemView.findViewById(R.id.quantity_textView)
//
//    }
//}