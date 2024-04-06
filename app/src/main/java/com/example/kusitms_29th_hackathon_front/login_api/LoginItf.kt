package com.example.kusitms_29th_hackathon_front.login_api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginItf {

    @POST("/user/signup")
    @FormUrlEncoded
    fun postSignUp(
        @Header("Authorization") authorization: String,
        @Field("enterprise") enterprise: String,
        @Field("type") type: String,
        @Field("email") email: String
    ): Call<SignUpResponse>

    @GET("/user/login")
    fun getLogIn(
        @Header("Authorization") idToken: String
    ): Call<getLogInResponse>

}