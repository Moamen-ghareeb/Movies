package com.example.movies

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task_.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMain2Binding.inflate(layoutInflater)
     setContentView(binding.root)

         sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor =sharedPref.edit()

        binding.button.setOnClickListener {
            val vintent = Intent(this@MainActivity2,MainActivity::class.java)
            val userName = binding.name.text.toString()
            editor.apply {
                putString("user_name", userName)
                apply()
            }
            intent.putExtra("Username", userName)
            startActivity(vintent)
        }
    }
}