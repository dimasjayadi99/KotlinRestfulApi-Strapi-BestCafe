package com.example.bestcafe.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.bestcafe.Api.User.ApiConfigUser
import com.example.bestcafe.Api.User.SharedPrefManager
import com.example.bestcafe.Api.User.UserModel
import com.example.bestcafe.R
import com.example.bestcafe.Response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {
    lateinit var et_email : EditText
    lateinit var et_username : EditText
    lateinit var et_telp : EditText
    lateinit var et_alamat : EditText
    lateinit var btn_edit : AppCompatButton

    var id : Int = 0
    var jwt : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        et_email = findViewById(R.id.et_email)
        et_username = findViewById(R.id.et_username)
        et_telp = findViewById(R.id.et_telp)
        et_alamat = findViewById(R.id.et_alamat)
        btn_edit = findViewById(R.id.btn_edit)

        val sharedPrefManager = SharedPrefManager
        id = sharedPrefManager.getInstance(this).user.id
        jwt = sharedPrefManager.getInstance(this).user.jwt.toString()
//        val email = sharedPrefManager.getInstance(this).user.email
//        val username = sharedPrefManager.getInstance(this).user.username
//        val telp = sharedPrefManager.getInstance(this).user.telp
//        val alamat = sharedPrefManager.getInstance(this).user.alamat

        ApiConfigUser.getService().getDataUser(id).enqueue(object : Callback<List<UserModel>>{
            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        val responseBody = response.body()
                        val email = responseBody?.get(0)?.email
                        val username = responseBody?.get(0)?.username
                        val telp = responseBody?.get(0)?.telp
                        val alamat = responseBody?.get(0)?.alamat

                        et_email.setText(email)
                        et_username.setText(username)
                        et_telp.setText(telp)
                        et_alamat.setText(alamat)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Toast.makeText(this@EditProfileActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })

        btn_edit.setOnClickListener {
            val user_email = et_email.text.toString()
            val user_username = et_username.text.toString()
            val user_telp = et_telp.text.toString()
            val user_alamat = et_alamat.text.toString()

            if (user_username.isEmpty()){
                et_username.error = "Blank Field!"
                et_username.requestFocus()
            }
            else{
                editDataUser(user_email,user_username,user_alamat,user_telp)
            }
        }


    }

    private fun editDataUser(user_email :String,userUsername: String, userAlamat: String, userTelp: String) {
        val requestBody = UserModel(id,user_email,userUsername,userTelp,userAlamat,jwt)
        ApiConfigUser.getService().updateUser(id,requestBody).enqueue(object : Callback<UserModel>{
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        Toast.makeText(this@EditProfileActivity, "Berhasil Update", Toast.LENGTH_SHORT).show()
                        onBackPressed()
                    }
                    else{
                        Toast.makeText(this@EditProfileActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@EditProfileActivity, response.message(), Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(this@EditProfileActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

}