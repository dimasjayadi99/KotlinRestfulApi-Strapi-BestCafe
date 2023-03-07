package com.example.bestcafe.Api.Food

import com.example.bestcafe.Response.ResponseFoods
import com.example.bestcafe.Response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceFood {
    @GET("foods/?populate=*")
    fun getRecommended() : Call<ResponseFoods>

    @GET("foods/?populate=*&filters[promo][\$eq]=true")
    fun getPromo() : Call<ResponseFoods>

    @GET("toppings/?populate=*&")
    fun getToppings(@Query("filters[foods][id][\$eq]=id") id : Int) : Call<ResponseFoods>

}