package com.example.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.task_.R

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent =Intent(this@Home,MainActivity2::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}