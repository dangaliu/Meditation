package com.example.meditation.retrofit

import com.example.meditation.model.dto.SignInBody
import com.example.meditation.model.dto.SignInResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MeditationApi {

    companion object {
        val instance: MeditationApi = Retrofit.Builder()
            .baseUrl("http://mskko2021.mad.hakta.pro/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MeditationApi::class.java)
    }

    @POST("user/login")
    fun signIn(@Body signInBody: SignInBody): Call<SignInResponse>
}