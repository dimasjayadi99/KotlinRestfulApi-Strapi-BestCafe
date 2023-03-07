package com.example.bestcafe.Api.User

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfigUser {
    const val base_url = "http://10.0.2.2:1337/api/"

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() : ApiServiceUser{
        return getRetrofit().create(ApiServiceUser::class.java)
    }
}