package com.example.bestcafe.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bestcafe.Activity.DetailActivity
import com.example.bestcafe.R
import com.example.bestcafe.Response.DataItem
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterRecommended(val listFood : List<DataItem?>?) : RecyclerView.Adapter<AdapterRecommended.MyViewHolder>() {
    class MyViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val img_thumb = view.findViewById<ImageView>(R.id.img_thumb)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val rb_rate = view.findViewById<RatingBar>(R.id.rb_rate)
        val price = view.findViewById<TextView>(R.id.tv_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommended,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (listFood != null){
            return listFood.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val thumbLink = "http://10.0.2.2:1337" + listFood?.get(position)?.attributes?.thumb?.data?.attributes?.url
        val price = listFood?.get(position)?.attributes?.price
        val localID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localID)
        numberFormat.minimumFractionDigits = 0
        holder.price.text = numberFormat.format(price)
        Picasso.get().load(thumbLink).into(holder.img_thumb)
        holder.name.text = listFood?.get(position)?.attributes?.name
        holder.rb_rate.rating = listFood?.get(position)?.attributes?.rate?.toFloat()!!

        val strCategory = ArrayList<String> ()

        for(i in listFood?.get(position)?.attributes?.categories?.data?.indices!!){
            listFood?.get(position)?.attributes?.categories?.data!![i]?.attributes?.name?.let {
                strCategory.add(it)
            }
        }

        holder.itemView.setOnClickListener{
            // send data to detail page
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("id",listFood?.get(position)?.id)
            intent.putExtra("thumb",thumbLink)
            intent.putExtra("name",holder.name.text)
            intent.putExtra("price",listFood?.get(position)?.attributes?.price)
            intent.putExtra("deskripsi", listFood?.get(position)?.attributes?.description)
            intent.putExtra("rate",listFood?.get(position)?.attributes?.rate)
            intent.putExtra("category",strCategory)
            it.context.startActivity(intent)
        }

    }
}