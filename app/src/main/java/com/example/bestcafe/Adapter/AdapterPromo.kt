package com.example.bestcafe.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bestcafe.R
import com.example.bestcafe.Response.DataItem
import com.squareup.picasso.Picasso

class AdapterPromo(val listPromo : List<DataItem?>?) : RecyclerView.Adapter<AdapterPromo.MyViewHolder>() {
    class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        val img_thumb_background = view.findViewById<ImageView>(R.id.img_thumb_background)
        val tv_promo_persen = view.findViewById<TextView>(R.id.tv_promo_persen)
        val tv_promo_duration = view.findViewById<TextView>(R.id.tv_promo_duration)
        val img_thumb = view.findViewById<ImageView>(R.id.img_thumb)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promo,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (listPromo != null){
            return listPromo.size.coerceAtMost(5)
        }
        return 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val thumbLink = "http://10.0.2.2:1337" + listPromo?.get(position)?.attributes?.thumb?.data?.attributes?.url
        val persen = listPromo?.get(position)?.attributes?.persen.toString()
        Picasso.get().load(thumbLink).into(holder.img_thumb_background)
        Picasso.get().load(thumbLink).into(holder.img_thumb)
        holder.tv_promo_persen.text = "UPTO\n$persen% OFF"
        holder.tv_promo_duration.text = listPromo?.get(position)?.attributes?.duration.toString()

    }
}