package com.example.kusitms_29th_hackathon_front.api_server

import retrofit2.Call
import retrofit2.http.*

interface AuthJoinService { // 회원가입
    // "api 경로"
    // 함수명(서버에 보내는 데이터:Body 변수명: 데이터타입): Call<서버에 받아오는 데이터 형태>
    @POST("auth/join")
    fun sendAuthRequest(@Body join: Join): Call<Void>
}


interface MiningService { // 질문 생성
    @POST("/mining")
    fun sendMiningRequest(@Body platformUrl: String): Call<Mining>
}

interface AddMiningService {
    @PATCH("/mining/{miningId}")
    fun addMiningRequest(@Path("miningId") miningId: Int,
                         @Body answer: String): Call<Void>
}