package com.example.kusitms_29th_hackathon_front.login_api

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("userId")
    val userId: Int
)