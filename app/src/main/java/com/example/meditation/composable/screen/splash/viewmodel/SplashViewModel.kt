package com.example.meditation.composable.screen.splash.viewmodel

import androidx.lifecycle.ViewModel
import com.example.meditation.model.dto.SignInBody
import com.example.meditation.model.shared_preferences.PrefRepository

class SplashViewModel(
    private val prefRepository: PrefRepository
): ViewModel() {
    fun getUser() : SignInBody {
        return SignInBody(
            email = prefRepository.getEmail() ?: "",
            password = prefRepository.getPassword() ?: ""
        )
    }
}