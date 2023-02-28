package com.example.bestcafe.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bestcafe.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener(this)
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

    override fun onClick(view: View?) {
        val intent = Intent(view?.context,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}