package com.example.kusitms_29th_hackathon_front

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMainBinding
import com.example.kusitms_29th_hackathon_front.mining.Mining1Activity
import com.example.kusitms_29th_hackathon_front.mining.MiningDetailActivity
import com.example.kusitms_29th_hackathon_front.mining.MiningDoneActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var new: Int ?= -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            val intent = Intent(this, Mining1Activity::class.java)
            startActivity(intent)
        }

        binding.myBtn.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        new = intent.getIntExtra("new", -1)

        Log.d("my log", ""+new)

        if(new == 1) {
            binding.done.visibility == View.VISIBLE
        } else {
            binding.done.visibility == View.GONE
        }

        binding.done.setOnClickListener {
            val intent = Intent(this, MiningDetailActivity::class.java)
            intent.putExtra("done", 1)
            startActivity(intent)
        }
    }
}