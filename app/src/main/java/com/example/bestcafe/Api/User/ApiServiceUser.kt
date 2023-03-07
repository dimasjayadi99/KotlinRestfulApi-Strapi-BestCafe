package com.example.bestcafe.Api.User

import com.example.bestcafe.Request.LoginRequest
import com.example.bestcafe.Request.RegisterRequest
import com.example.bestcafe.Response.ResponseUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceUser {
    @POST("auth/local/register")
    fun registerAccount(@Body body : RegisterRequest) : Call<ResponseUser>

    @POST("auth/local")
    fun loginAccount(@Body body : LoginRequest) : Call<ResponseUser>

    @GET("users?")
    fun getDataUser(@Query("filters[id][\$eq]=id") id : Int) : Call<List<UserModel>>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id : Int?, @Body body : UserModel) : Call<UserModel>

}