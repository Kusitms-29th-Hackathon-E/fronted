package com.example.kusitms_29th_hackathon_front.mining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMiningDoneBinding

class MiningDoneActivity : AppCompatActivity() {
    lateinit var binding: ActivityMiningDoneBinding
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiningDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2초 후에 Mining3Activity로 이동
        handler.postDelayed({
            val intent = Intent(this, Mining3Activity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

        binding.closeBtn.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        // Activity가 소멸될 때 Handler의 작업을 취소하여 메모리 누수를 방지
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}