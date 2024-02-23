package com.gallardo.cyber_cartel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class home_page_categories_adapter constructor(private val getActivity: Rv_Home_Page, private val iconsList : List<home_page_categories_DC> ):
    RecyclerView.Adapter<home_page_categories_adapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_icon, parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return iconsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.icon.setImageResource(iconsList[position].Image)

    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val icon : ImageView = itemView.findViewById(R.id.image_categories)

    }


}