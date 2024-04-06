package com.example.kusitms_29th_hackathon_front.mining

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.example.kusitms_29th_hackathon_front.R
import com.example.kusitms_29th_hackathon_front.api_server.AddMiningManager
import com.example.kusitms_29th_hackathon_front.databinding.ActivityMining2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Mining2Activity : AppCompatActivity() {
    lateinit var binding: ActivityMining2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMining2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mining1Activity로부터 전달받은 데이터 추출
        val question = intent.getStringExtra("question")
        val miningId = intent.getIntExtra("miningId", 0) // miningId가 Int 타입이므로 디폴트 값으로 0 설정

        Log.d("my log", "2액티비티"+question+miningId)
        binding.question.setText(question)

        // EditText에 TextWatcher 추가
        binding.content.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // EditText의 텍스트가 변경될 때마다 호출되며, 텍스트의 길이를 확인하여 버튼 활성화 여부 결정
                val text = s.toString()
                binding.nextBtn.isEnabled = text.isNotEmpty()
                if (text.isNotEmpty()) {
                    // 텍스트가 입력되면 버튼 색상 및 텍스트 색상 변경
                    binding.nextBtn.setBackgroundResource(R.drawable.btn_primary)
                    binding.nextBtn.setTextColor(resources.getColor(R.color.gray20))
                } else {
                    // 텍스트가 없으면 버튼 비활성화
                    binding.nextBtn.setBackgroundResource(R.drawable.btn_gray)
                    binding.nextBtn.setTextColor(resources.getColor(R.color.gray60))
                }
            }
        })

        binding.nextBtn.setOnClickListener {
            val answer = binding.content.text.toString()

            Log.d("my log", ""+ answer)

            AddMiningManager.addMiningService(miningId!!, answer!!, object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        val mining = response.body()
                        mining?.let {
                        }
                        val intent = Intent(this@Mining2Activity, MiningDoneActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                        finish()
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("서버 테스트", "오류1: $errorBody")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("서버 테스트", "오류2: ${t.message}")
                }
            })
        }

        binding.root.setOnClickListener {
            // 화면의 다른 부분을 클릭하면 EditText의 포커스를 해제하고 키보드를 내림
            hideKeyboard()
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun hideKeyboard() {
        binding.content.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}