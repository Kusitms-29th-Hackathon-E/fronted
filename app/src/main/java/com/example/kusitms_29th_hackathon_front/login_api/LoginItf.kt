package com.example.kusitms_29th_hackathon_front.login_api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginItf {

    // 회원가입
    @POST("/oauth/KAKAO")
    fun postSignUp(
        @Header("OAuth-AccessToken") authorization: String
    ): Call<Void>

    // 로그인 정보 확인
    @GET("/oauth/KAKAO")
    fun getLogIn(
        @Header("OAuth-AccessToken") accessToken: String
    ): Call<getLogInResponse>

}