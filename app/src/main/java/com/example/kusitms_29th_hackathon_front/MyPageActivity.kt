package com.example.kusitms_29th_hackathon_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMainBinding
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

    }
}