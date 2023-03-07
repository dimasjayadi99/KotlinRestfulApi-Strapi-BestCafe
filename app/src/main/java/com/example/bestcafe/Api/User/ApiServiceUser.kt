package com.example.bestcafe.Api.User

import com.example.bestcafe.Request.LoginRequest
import com.example.bestcafe.Request.RegisterRequest
import com.example.bestcafe.Response.ResponseUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceUser {
    @POST("auth/local/register")
    fun registerAccount(@Body body : RegisterRequest) : Call<ResponseUser>

    @POST("auth/local")
    fun loginAccount(@Body body : LoginRequest) : Call<ResponseUser>
}