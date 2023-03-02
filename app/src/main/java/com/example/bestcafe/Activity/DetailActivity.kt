package com.example.bestcafe.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestcafe.Adapter.AdapterCategory
import com.example.bestcafe.R
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    lateinit var img_thumb : ImageView
    lateinit var tv_name : TextView
    lateinit var tv_price : TextView
    lateinit var rating_bar : RatingBar
    lateinit var tv_deskripsi : TextView
    lateinit var rv_category : RecyclerView

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        img_thumb = findViewById(R.id.img_thumb)
        tv_name = findViewById(R.id.tv_name)
        tv_price = findViewById(R.id.tv_price)
        tv_deskripsi = findViewById(R.id.tv_deskripsi)
        rating_bar = findViewById(R.id.ratingBar)
        rv_category = findViewById(R.id.rv_category)

        val thumb = intent.getStringExtra("thumb")
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("deskripsi")
        val price = intent.getIntExtra("price",0)
        val rate = intent.getIntExtra("rate",0)
        val categories = intent.getStringArrayListExtra("category") as ArrayList<String>

        Picasso.get().load(thumb).into(img_thumb)
        tv_name.text = name
        rating_bar.rating = rate.toFloat()
        tv_deskripsi.text = description
        val localID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localID)
        tv_price.text = numberFormat.format(price)

        val adapterListCategory = AdapterCategory(categories)
        rv_category.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapterListCategory.notifyDataSetChanged()
            adapter = adapterListCategory
        }

    }
}