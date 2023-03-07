package com.example.bestcafe.Request

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @field:SerializedName("identifier")
    val identifier : String? = null,

    @field:SerializedName("password")
    val password : String? = null
)