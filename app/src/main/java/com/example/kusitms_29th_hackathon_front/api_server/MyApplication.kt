package com.example.kusitms_29th_hackathon_front.api_server

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class MyApplication {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://3.37.99.87") // 서버 URL
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .client(OkHttpClient.Builder().build())
        .build()

    fun getGson(): Gson {
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault()) // 원하는 시간 형식 지정

        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd") // Date 형식 지정
            .create()
    }

    val authJoinService = retrofit.create(AuthJoinService::class.java)
}