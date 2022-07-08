package com.example.meditation.composable.screen.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditation.model.shared_preferences.PrefRepository

class OnBoardingFactory(
    private val prefRepository: PrefRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnBoardingViewModel(prefRepository) as T
    }
}