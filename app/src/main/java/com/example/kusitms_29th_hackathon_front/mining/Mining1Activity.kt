package com.example.kusitms_29th_hackathon_front.mining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMining1Binding

class Mining1Activity : AppCompatActivity() {
    lateinit var binding: ActivityMining1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMining1Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}