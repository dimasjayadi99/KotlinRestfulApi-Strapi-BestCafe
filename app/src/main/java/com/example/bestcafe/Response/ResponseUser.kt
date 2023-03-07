package com.example.bestcafe.Response

import com.google.gson.annotations.SerializedName

data class ResponseUser(

	@field:SerializedName("jwt")
	val jwt: String? = null,

	@field:SerializedName("user")
	val user: User? = null
)

data class User(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("blocked")
	val blocked: Boolean? = null,

	@field:SerializedName("provider")
	val provider: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("confirmed")
	val confirmed: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
