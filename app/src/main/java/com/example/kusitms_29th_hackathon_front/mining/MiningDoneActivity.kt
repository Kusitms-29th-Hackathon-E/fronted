package com.example.kusitms_29th_hackathon_front.mining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMiningDoneBinding

class MiningDoneActivity : AppCompatActivity() {
    lateinit var binding: ActivityMiningDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiningDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.closeBtn.setOnClickListener {
            finish()
        }
    }
}