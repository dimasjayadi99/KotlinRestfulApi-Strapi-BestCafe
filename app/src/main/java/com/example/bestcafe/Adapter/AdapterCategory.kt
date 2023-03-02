package com.example.bestcafe.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bestcafe.R

class AdapterCategory(val listCategory: ArrayList<String>) : RecyclerView.Adapter<AdapterCategory.MyViewHolder>() {
    class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (listCategory != null){
            return listCategory.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = listCategory?.get(position)
    }
}