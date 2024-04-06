package com.example.kusitms_29th_hackathon_front.login_api

data class getLogInResponse(
    val code: Int,
    val message: String,
    val result: ResultX,
    val status: Int
)