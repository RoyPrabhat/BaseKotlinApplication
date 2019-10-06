package com.example.wellthydemoapp.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity
import com.example.wellthydemoapp.R
import com.example.wellthydemoapp.view.prodlist.ProductListActivity


class SplashActivity : AppCompatActivity() {

    val SPLASH_SCREEN_TIME = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, ProductListActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN_TIME.toLong())
    }
}