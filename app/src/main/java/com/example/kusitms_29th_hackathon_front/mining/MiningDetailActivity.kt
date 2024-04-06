package com.example.kusitms_29th_hackathon_front.mining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMiningDetailBinding

class MiningDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMiningDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiningDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}