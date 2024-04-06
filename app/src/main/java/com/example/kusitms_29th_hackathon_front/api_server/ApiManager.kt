package com.example.kusitms_29th_hackathon_front.api_server

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object JoinManager {
    fun sendJoinToServer(join: Join) {
        val apiService = MyApplication().authJoinService
        val call = apiService.sendAuthRequest(join)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
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
}

object MiningManager {
    fun miningService(platformUrl: String, callback: Callback<Mining>) {
        val apiService = MyApplication().miningService
        val call = apiService.sendMiningRequest(platformUrl)
        call.enqueue(callback)
    }
}

object AddMiningManager {
    fun addMiningService(miningId: Int, answer: String, callback: Callback<Void>) {
        val apiService = MyApplication().addMiningService
        val call = apiService.addMiningRequest(miningId, answer)
        call.enqueue(callback)
    }
}