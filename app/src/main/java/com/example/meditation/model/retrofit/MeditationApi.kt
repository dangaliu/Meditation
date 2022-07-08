package com.example.meditation.model.retrofit

import com.example.meditation.model.dto.FeelingResponse
import com.example.meditation.model.dto.QuoteResponse
import com.example.meditation.model.dto.SignInBody
import com.example.meditation.model.dto.SignInResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
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
    suspend fun signIn(@Body signInBody: SignInBody): Response<SignInResponse>

    @GET("feelings")
    suspend fun getFeelings(): Response<FeelingResponse>

    @GET("quotes")
    suspend fun getQuotes(): Response<QuoteResponse>
}