package com.gallardo.cyber_cartel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ViewPager_Adapter  (var img:List<Int>):
    RecyclerView.Adapter<ViewPager_Adapter.PageViewHolder>() {

    class PageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.images,parent,false))
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.imageView.setImageResource(img[position])
    }

    override fun getItemCount(): Int {
        return img.size
    }
}