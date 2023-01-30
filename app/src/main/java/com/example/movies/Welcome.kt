package com.example.movies


import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.task_.databinding.FragmentWelcomeBinding


class Welcome : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        sharedPref = activity?.getSharedPreferences("myPref", AppCompatActivity.MODE_PRIVATE)!!
        val name = sharedPref.getString("user_name","")
        binding.name2.text=name.toString()
        return binding.root
    }
}