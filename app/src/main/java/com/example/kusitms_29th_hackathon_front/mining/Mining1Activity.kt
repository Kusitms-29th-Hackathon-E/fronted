package com.example.kusitms_29th_hackathon_front.mining

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.kusitms_29th_hackathon_front.R
import com.example.kusitms_29th_hackathon_front.api_server.Mining
import com.example.kusitms_29th_hackathon_front.api_server.MiningManager
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMining1Binding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Matcher
import java.util.regex.Pattern

class Mining1Activity : AppCompatActivity() {
    lateinit var binding: ActivityMining1Binding
    var videoId: String ?= null
    var platformUrl: String ?= null
    var question: String ?= null
    var miningId: Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMining1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // num 텍스트뷰에 1부터 5까지의 랜덤 숫자 설정
        val randomNumber = (1..5).random()
        binding.num.text = randomNumber.toString()

        // EditText에 TextWatcher 추가
        binding.urlText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.youtubePlayerView.visibility = View.GONE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트가 변경될 때마다 URL에서 videoId 추출하여 동영상 재생
                videoId = extractVideoIdFromUrl(binding.urlText.text.toString())
                platformUrl = binding.urlText.text.toString()

                Log.d("my log", ""+videoId)

                playYouTubeVideo(videoId!!)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // YouTubePlayerView 초기화
        lifecycle.addObserver(binding.youtubePlayerView)

        // 초기 동영상 재생을 위한 빈 videoId로 재생 시작
        //playYouTubeVideo("")

        binding.nextBtn.setOnClickListener {
            Log.d("my log", ""+platformUrl)

            MiningManager.miningService(platformUrl!!, object : Callback<Mining> {
                override fun onResponse(call: Call<Mining>, response: Response<Mining>) {
                    if (response.isSuccessful) {
                        val mining = response.body()
                        mining?.let {
                            // 서버에서 받은 데이터 처리
                            Log.d("Mining", "Question: ${mining.question}, Mining ID: ${mining.miningId}")

                            question = mining.question
                            miningId = mining.miningId

                            // 응답을 받은 후에 Intent를 시작
                            val intent = Intent(this@Mining1Activity, Mining2Activity::class.java)
                            intent.putExtra("question", question)
                            intent.putExtra("miningId", miningId)
                            startActivity(intent)
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("서버 테스트", "오류1: $errorBody")
                    }
                }

                override fun onFailure(call: Call<Mining>, t: Throwable) {
                    Log.e("서버 테스트", "오류2: ${t.message}")
                }
            })

//            val intent = Intent(this, Mining2Activity::class.java)
//            Log.d("my log", "1액티비티"+question+miningId)
//            intent.putExtra("question", question)
//            intent.putExtra("miningId", miningId)
//            startActivity(intent)
        }

        binding.root.setOnClickListener {
            // 화면의 다른 부분을 클릭하면 EditText의 포커스를 해제하고 키보드를 내림
            hideKeyboard()
        }

        binding.closeBtn.setOnClickListener {
            finish()
        }
    }

    private fun playYouTubeVideo(videoId: String) {
        binding.youtubePlayerView.visibility = View.VISIBLE

        lifecycle.addObserver(binding.youtubePlayerView)

        // YouTubePlayer를 초기화하여 동영상 재생
        binding.youtubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)

                binding.nextBtn.isEnabled = true // 유튜브 링크가 유효한 경우 버튼을 활성화
                binding.nextBtn.setBackgroundResource(R.drawable.btn_primary) // 버튼 색상 변경
                binding.nextBtn.setTextColor(resources.getColor(R.color.gray20))
            }
        })
    }

    private fun extractVideoIdFromUrl(url: String): String {
        val pattern = "(?<=\\/shorts\\/|watch\\?v=|\\/videos\\/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2Fvideos%2F|youtu.be%2F|watch\\?v=|embed\\?video_id=|&v=)([a-zA-Z0-9_-]{11})"
        val compiledPattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher = compiledPattern.matcher(url)
        return if (matcher.find()) {
            matcher.group()
        } else {
            ""
        }
    }

    private fun hideKeyboard() {
        binding.urlText.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}