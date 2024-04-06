package com.example.kusitms_29th_hackathon_front.api_server

data class User(
    val userId: Int,
    val username: String,
    val identify: String,
    val nickname: String,
    val password: String,
    val address: String,
    val phoneNumber: String,
    val image: String
)

data class Join(
    val username: String,
    val nickname: String,
    val identify: String,
    val address: String,
    val password: String,
    val phoneNumber: String
)
