package com.example.bestcafe.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bestcafe.R
import com.example.bestcafe.Response.DataItem
import java.text.NumberFormat
import java.util.*

class AdapterTopping(val listTopping: List<DataItem?>?) : RecyclerView.Adapter<AdapterTopping.MyViewHolder>() {
    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val checkbox = view.findViewById<CheckBox>(R.id.ch_box)
        val price_topping = view.findViewById<TextView>(R.id.tv_price_topping)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topping,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (listTopping != null){
            return listTopping.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.checkbox.text = listTopping?.get(position)?.attributes?.name

        val priceTopping = listTopping?.get(position)?.attributes?.price
        val localID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localID)
        numberFormat.minimumFractionDigits = 0
        holder.price_topping.text = numberFormat.format(priceTopping)
    }
}