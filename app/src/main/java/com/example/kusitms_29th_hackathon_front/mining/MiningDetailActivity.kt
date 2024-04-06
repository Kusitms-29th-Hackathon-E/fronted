package com.example.kusitms_29th_hackathon_front.mining

import android.content.Context
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.kusitms_29th_hackathon_front.R
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMiningDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback

class MiningDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMiningDetailBinding
    var new: Int ?= -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiningDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playYouTubeVideo("t9vBtX499pA")

        binding.closeBtn.setOnClickListener {
            finish()
        }

        new = intent.getIntExtra("done", -1)

        if (new == 1) {
            binding.content.visibility = View.GONE
        }

        // EditText에 TextWatcher 추가
        binding.content.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // EditText의 텍스트가 변경될 때마다 호출되며, 텍스트의 길이를 확인하여 버튼 활성화 여부 결정
                val text = s.toString()
                if (text.isNotEmpty()) {
                    // 텍스트가 입력되면 버튼 색상 및 텍스트 색상 변경
                    binding.done.visibility = View.VISIBLE
                } else {
                    // 텍스트가 없으면 버튼 비활성화
                    binding.done.visibility = View.GONE
                }
            }
        })

        binding.done.setOnClickListener {
            binding.layout.visibility = View.VISIBLE

            // EditText에서 입력된 텍스트 가져오기
            val answerText = binding.content.text.toString()

            // 가져온 텍스트를 answer 텍스트 뷰에 설정하기
            binding.answer.text = answerText

            // EditText의 포커스 제거
            binding.content.clearFocus()
        }

        binding.root.setOnClickListener {
            // 화면의 다른 부분을 클릭하면 EditText의 포커스를 해제하고 키보드를 내림
            hideKeyboard()
        }

    }

    private fun playYouTubeVideo(videoId: String) {
        binding.youtubePlayerView.visibility = View.VISIBLE

        lifecycle.addObserver(binding.youtubePlayerView)

        // YouTubePlayer를 초기화하여 동영상 재생
        binding.youtubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }

    private fun hideKeyboard() {
        binding.content.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}