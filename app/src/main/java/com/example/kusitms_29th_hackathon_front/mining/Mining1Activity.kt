package com.example.kusitms_29th_hackathon_front.mining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kusitms_29th_hackathon_front.R
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMining1Binding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class Mining1Activity : AppCompatActivity() {
    lateinit var binding: ActivityMining1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMining1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // XML 레이아웃에서 정의된 YouTubePlayerView 요소를 찾아옵니다.
        val youtubePlayerView: YouTubePlayerView = binding.youtubePlayerView

        // 액티비티 또는 프래그먼트의 라이프사이클을 관찰하여
        // YouTubePlayerView를 라이프사이클 이벤트와 동기화하도록 설정합니다.
        lifecycle.addObserver(youtubePlayerView)

        // YouTubePlayerListener를 구현한 익명 클래스를 생성하고, 리스너 추가
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {

            // YouTube 플레이어가 준비되면 호출되는 콜백 메서드입니다.
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // 재생할 동영상의 ID를 설정합니다.
                val videoId = "B9EsAERudp8"

                // 설정한 동영상 ID로 동영상을 로드하고 재생합니다.
                // 시작 시간을 0초로 설정하여 동영상을 처음부터 재생합니다.
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }
}