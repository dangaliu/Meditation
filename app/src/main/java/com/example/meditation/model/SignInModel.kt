package com.example.meditation.model

import com.example.meditation.model.dto.SignInBody
import com.example.meditation.retrofit.MeditationApi

class SignInModel {
    fun signIn(signInBody: SignInBody) = MeditationApi.instance.signIn(signInBody)
}