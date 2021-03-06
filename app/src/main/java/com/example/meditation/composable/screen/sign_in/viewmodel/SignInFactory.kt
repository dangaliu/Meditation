package com.example.meditation.composable.screen.sign_in.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditation.model.SignInModel
import com.example.meditation.model.shared_preferences.PrefRepository

class SignInFactory(
    private val signInModel: SignInModel,
    private val prefRepository: PrefRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(signInModel, prefRepository) as T
    }
}