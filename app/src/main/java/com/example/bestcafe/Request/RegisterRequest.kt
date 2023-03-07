package com.example.bestcafe.Request

import com.google.gson.annotations.SerializedName

class RegisterRequest (

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

)