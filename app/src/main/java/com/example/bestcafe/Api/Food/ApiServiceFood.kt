package com.example.bestcafe.Api.Food

import com.example.bestcafe.Response.ResponseFoods
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceFood {
    @GET("foods/?populate=*")
    fun getRecommended() : Call<ResponseFoods>

    @GET("foods/?populate=*&filters[promo][\$eq]=true")
    fun getPromo() : Call<ResponseFoods>

}