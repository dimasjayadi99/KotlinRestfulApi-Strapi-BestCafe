package com.example.bestcafe.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bestcafe.Api.User.SharedPrefManager
import com.example.bestcafe.R

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btn_login : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        btn_login = findViewById(R.id.btn_login)
        btn_login.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent = Intent(p0?.context,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun toRegisterPage(p0: View) {
        val intent = Intent(p0.context,RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}