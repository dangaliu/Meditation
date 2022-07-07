package com.example.meditation.model

import com.example.meditation.model.dto.SignInBody
import com.example.meditation.model.retrofit.MeditationApi

class SignInModel {
    suspend fun signIn(signInBody: SignInBody) = MeditationApi.instance.signIn(signInBody)
}