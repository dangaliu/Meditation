package com.example.meditation.model

import com.example.meditation.model.retrofit.MeditationApi

class MainModel {
    suspend fun getFeelings() = MeditationApi.instance.getFeelings()
    suspend fun getQuotes() = MeditationApi.instance.getQuotes()
}