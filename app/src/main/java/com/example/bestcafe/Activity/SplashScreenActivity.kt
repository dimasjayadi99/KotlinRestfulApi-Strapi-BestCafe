package com.example.bestcafe.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.bestcafe.R

class SplashScreenActivity : AppCompatActivity() {

    val timeload : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val intent = Intent(this,WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        },timeload)
    }
}