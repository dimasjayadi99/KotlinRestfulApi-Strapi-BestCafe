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
import com.example.bestcafe.Api.User.SharedPrefManager
import com.example.bestcafe.R
import com.example.bestcafe.Request.LoginRequest
import com.example.bestcafe.Response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var et_email : EditText
    lateinit var et_password : EditText
    lateinit var btn_login : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        btn_login = findViewById(R.id.btn_login)
        btn_login.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            if (email.isEmpty()){
                et_email.error = "Blank Field"
                et_email.requestFocus()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email.error = "Email not valid!"
                et_email.requestFocus()
            }
            else if (password.isEmpty()){
                et_password.error = "Blank Field"
                et_password.requestFocus()
            }
            else if (password.length < 8){
                et_password.error = "Min 8 Char"
                et_password.requestFocus()
            }
            else{
                loginAccount(email,password)
            }
        }

    }

    private fun loginAccount(email: String, password: String) {
        val loginReq = LoginRequest(email,password)
        ApiConfigUser.getService().loginAccount(loginReq).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        SharedPrefManager.getInstance(applicationContext).saveUser(response.body())
                        val intent = Intent(applicationContext,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                }else{
                    Toast.makeText(this@LoginActivity, "Gagal Login!, Silahkan masukan email dan password terdaftar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun toRegister(view: View) {
        val intent = Intent(view.context,RegisterActivity::class.java)
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