package com.example.bestcafe.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.bestcafe.Api.User.ApiConfigUser
import com.example.bestcafe.R
import com.example.bestcafe.Request.RegisterRequest
import com.example.bestcafe.Response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var et_username : EditText
    lateinit var et_email : EditText
    lateinit var et_password : EditText
    lateinit var btn_register : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        et_username = findViewById(R.id.et_username)
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        btn_register = findViewById(R.id.btn_register)

        btn_register.setOnClickListener {
            val username = et_username.text.toString()
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            if (username.isEmpty()){
                et_username.error = "Blank Field!"
                et_username.isFocusable
            }
            else if(email.isEmpty()){
                et_email.error = "Blank Field!"
                et_email.isFocusable
            }
            else if(password.isEmpty()){
                et_password.error = "Blank Field!"
                et_password.isFocusable
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email.error = "Email format not valid!"
                et_email.isFocusable
            }
            else if(password.length < 8){
                et_password.error = "Min 8 char"
                et_password.isFocusable
            }
            else{
                funRegiterAccount(username,email,password)
            }

        }

    }

    private fun funRegiterAccount(username: String, email: String, password: String) {
        val userReq = RegisterRequest(username,email,password)
        ApiConfigUser.getService().registerAccount(userReq).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                Toast.makeText(this@RegisterActivity, "Berhasil Membuat Akun", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun toLogin(view: View) {
        val intent = Intent(view.context,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this,WelcomeActivity::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }

}