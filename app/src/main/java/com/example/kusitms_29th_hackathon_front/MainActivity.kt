package com.example.kusitms_29th_hackathon_front

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMainBinding
import com.example.kusitms_29th_hackathon_front.mining.Mining1Activity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            val intent = Intent(this, Mining1Activity::class.java)
            startActivity(intent)
        }

    }
}