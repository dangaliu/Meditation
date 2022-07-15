package com.example.meditation.composable.screen.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditation.model.shared_preferences.PrefRepository

class SplashFactory(
    private val privateRepository: PrefRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(prefRepository = privateRepository) as T
    }
}