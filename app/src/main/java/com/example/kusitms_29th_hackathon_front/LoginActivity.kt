package com.example.kusitms_29th_hackathon_front

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kusitms_29th_hackathon_front.BuildConfig.KAKAO_APP_KEY
import com.example.kusitms_29th_hackathon_front.databinding.ActivityLoginBinding
import com.example.kusitms_29th_hackathon_front.login_api.RetrofitClient
import com.example.kusitms_29th_hackathon_front.login_api.getLogInResponse
import com.google.gson.Gson
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 저장된 토큰 꺼내기
        val sharedPreferences = getSharedPreferences("my_token", Context.MODE_PRIVATE)
        val accessToken = sharedPreferences.getString("access_token", null)

        Log.d("my log", ""+accessToken)

        KakaoSdk.init(this, "${KAKAO_APP_KEY}")

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                // 로그인 실패
                Log.d("my log", "로그인 실패")
            }
            else if (token != null){
                // 로그인 성공
                Log.d("my log", "로그인 토큰 테스트" + token)

                // 토큰 저장
                val sharedPreferences = this.getSharedPreferences("my_token", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                val gson = Gson()
                val tokenJson = gson.toJson(token)

                editor.putString("kakao_token", tokenJson)  // 카카오 토큰 전체
                editor.putString("access_token", token.accessToken) // 액세스 토큰
                editor.apply()

                RetrofitClient.login.getLogIn("Bearer ${token.idToken}").enqueue(object :
                    Callback<getLogInResponse> {
                    override fun onResponse(call: Call<getLogInResponse>, response: Response<getLogInResponse>) {
                        Log.d("토큰", token.idToken.toString())
                        if (response.isSuccessful) {
                            val logInResponse = response.body()
                            Log.d("성공",response.body().toString())
                            if (logInResponse != null) {
                                if (logInResponse.result.isUser) {
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                                    finish()
                                } else {
                                    //val intent = Intent(this@LoginActivity, OnboardingActivity::class.java)
//                                    intent.putExtra("accessToken", logInResponse.result.accessToken)
//                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                                    finish()
                                }
                            }
                        } else {
                            // 회원가입 실패
                            val errorMessage = "요청 실패: ${response.code()} ${response.message()}"
                            Log.e("API 요청 실패", errorMessage)
                            // 추가 정보 출력
                            try {
                                val errorBody = response.errorBody()?.string()
                                Log.e("API 응답 에러", errorBody ?: "에러 응답 본문이 없습니다.")
                            } catch (e: Exception) {
                                Log.e("API 응답 에러", "에러 본문을 읽는 중 에러가 발생했습니다.")
                            }
                        }
                    }

                    override fun onFailure(call: Call<getLogInResponse>, t: Throwable) {
                        // 네트워크 오류 등으로 요청 실패 처리
                        Log.e("로그인 호출 실패", "요청 실패: ${t.message}", t)
                    }
                })
            }
        }

        binding.loginBtn.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }
}