package com.gallardo.cyber_cartel

import android.content.Intent
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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.icon.setImageResource(iconsList[position].Image)
        holder.icon.setOnClickListener{
            if(position == 0){
                val int = Intent(getActivity,Category_Cpu::class.java)
                getActivity.startActivity(int)
            } else if (position == 1){
                val int = Intent(getActivity,Category_Gpu::class.java)
                getActivity.startActivity(int)
                getActivity.finish()
            } else if (position == 2){
                val int = Intent(getActivity,Category_Hdd::class.java)
                getActivity.startActivity(int)
            } else if (position == 3){
                val int = Intent(getActivity,Category_MoBo::class.java)
                getActivity.startActivity(int)
            } else if (position == 4){
                val int = Intent(getActivity,Category_Psu::class.java)
                getActivity.startActivity(int)
            } else if (position == 5){
                val int = Intent(getActivity,Category_Ram::class.java)
                getActivity.startActivity(int)
            } else {
                val int = Intent(getActivity,Category_Ssd::class.java)
                getActivity.startActivity(int)
            }
        }
    }

    override fun getItemCount(): Int {
        return iconsList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val icon : ImageView = itemView.findViewById(R.id.image_categories)

    }


}