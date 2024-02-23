package com.gallardo.cyber_cartel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.cyber_cartel.DataClass.User_Addresses_DC
import com.gallardo.cyber_cartel.R
import com.gallardo.cyber_cartel.User_Addresses

class User_Addresses_Adapter(private val getActivity: User_Addresses, private val Address: ArrayList<User_Addresses_DC>):
    RecyclerView.Adapter<User_Addresses_Adapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): User_Addresses_Adapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.address_holder, parent, false)

        return User_Addresses_Adapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_FullName.text = Address[position].FullName
        holder.tv_PhoneNumber.text = Address[position].PhoneNumber.toString()
        holder.tv_Street_Name.text = Address[position].StreetName
        holder.tv_House_Number.text = Address[position].HouseNumber.toString()
        holder.tv_Barangay.text = Address[position].Barangay
        holder.tv_Municipality.text = Address[position].Municipality
        holder.tv_Province.text = Address[position].Province
        holder.tv_Postal_Code.text = Address[position].Postal_Code.toString()

        holder.cardView_ToReceive.setOnClickListener{
            Toast.makeText(getActivity,Address[position].FullName, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return Address.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv_FullName : TextView = itemView.findViewById(R.id.tv_FullName)
        val tv_PhoneNumber : TextView = itemView.findViewById(R.id.tv_PhoneNumber)
        val tv_House_Number : TextView = itemView.findViewById(R.id.tv_House_Number)
        val tv_Street_Name : TextView = itemView.findViewById(R.id.tv_Street)
        val tv_Barangay : TextView = itemView.findViewById(R.id.tv_Barangay)
        val tv_Municipality :TextView = itemView.findViewById(R.id.tv_Municipality)
        val tv_Province : TextView = itemView.findViewById(R.id.tv_Province)
        val tv_Postal_Code : TextView = itemView.findViewById(R.id.tv_PostalCode)
        val cardView_ToReceive : CardView = itemView.findViewById(R.id.CardView_Address)
    }
}