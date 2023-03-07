package com.example.bestcafe.Activity

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestcafe.Adapter.AdapterCategory
import com.example.bestcafe.Adapter.AdapterTopping
import com.example.bestcafe.Api.Food.ApiConfigFood
import com.example.bestcafe.R
import com.example.bestcafe.Response.ResponseFoods
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailActivity : AppCompatActivity() {

    lateinit var img_thumb : ImageView
    lateinit var tv_name : TextView
    lateinit var tv_price : TextView
    lateinit var rating_bar : RatingBar
    lateinit var tv_deskripsi : TextView
    lateinit var tv_notFound : TextView
    lateinit var rv_category : RecyclerView
    lateinit var rv_topping : RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        img_thumb = findViewById(R.id.img_thumb)
        tv_name = findViewById(R.id.tv_name)
        tv_price = findViewById(R.id.tv_price)
        tv_deskripsi = findViewById(R.id.tv_deskripsi)
        tv_notFound = findViewById(R.id.tv_notFound)
        tv_notFound.visibility = View.GONE
        rating_bar = findViewById(R.id.ratingBar)
        rv_category = findViewById(R.id.rv_category)
        rv_topping = findViewById(R.id.rv_topping)

        val id = intent.getIntExtra("id",0)
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
        numberFormat.minimumFractionDigits = 0
        tv_price.text = numberFormat.format(price)

        val adapterListCategory = AdapterCategory(categories)
        rv_category.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapterListCategory.notifyDataSetChanged()
            adapter = adapterListCategory
        }

        ApiConfigFood.getService().getToppings(id).enqueue(object : Callback<ResponseFoods>{
            override fun onResponse(call: Call<ResponseFoods>, response: Response<ResponseFoods>) {
                if (response.isSuccessful){
                    if (response.body()?.data?.isNotEmpty() == true){
                        tv_notFound.visibility = View.GONE
                        val responseBody = response.body()
                        val responseList = responseBody?.data
                        val adapterTopping = AdapterTopping(responseList)
                        rv_topping.apply {
                            layoutManager = LinearLayoutManager(this@DetailActivity)
                            setHasFixedSize(true)
                            adapterTopping.notifyDataSetChanged()
                            adapter = adapterTopping
                        }
                    }
                    else{
                        tv_notFound.visibility = View.VISIBLE
                    }
                }
                else{
                    tv_notFound.text = "Gagal memuat data\nterjadi kesalahan jaringan atau server"
                    tv_notFound.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<ResponseFoods>, t: Throwable) {
                tv_notFound.text = "Gagal memuat data\nterjadi kesalahan jaringan atau server"
                tv_notFound.visibility = View.VISIBLE
                Toast.makeText(this@DetailActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }
}