package com.example.meditation.composable.screen.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditation.model.shared_preferences.PrefRepository

class ProfileFactory(
    private val prefRepository: PrefRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(prefRepository = prefRepository) as T
    }
}