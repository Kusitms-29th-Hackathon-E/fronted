package com.example.kusitms_29th_hackathon_front.mining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kusitms_29th_hackathon_front.MainActivity
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMining3Binding

class Mining3Activity : AppCompatActivity() {
    lateinit var binding: ActivityMining3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMining3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.nextBtn.setOnClickListener {
            // MainActivity를 시작하고, 스택에 쌓인 모든 액티비티를 종료
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        binding.list.setOnClickListener {

        }
    }
}