package com.example.meditation.composable.screen.sign_in.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditation.model.SignInModel

class SignInFactory(
    private val signInModel: SignInModel
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(signInModel) as T
    }
}