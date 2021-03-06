package com.example.meditation.composable.screen.sign_in.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditation.model.SignInModel
import com.example.meditation.model.dto.SignInBody
import com.example.meditation.model.dto.SignInResponse
import com.example.meditation.model.shared_preferences.PrefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SignInViewModel(
    private val signInModel: SignInModel,
    private val prefRepository: PrefRepository
) : ViewModel() {

    val signInResponse = MutableLiveData<SignInResponse>()

    fun signIn(signInBody: SignInBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<SignInResponse> = signInModel.signIn(signInBody)
            if (response.isSuccessful) {
                signInResponse.postValue(response.body())
                response.body()?.let { prefRepository.putName(it.nickName) }
                response.body()?.let { prefRepository.putAvatar(it.avatar) }
            }
        }
    }

    fun isSignInFieldsValid(signInBody: SignInBody): Boolean {
        return !(signInBody.email.isEmpty() || signInBody.password.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(
            signInBody.email
        ).matches())
    }

    fun saveUser(signIn: SignInBody) {
        prefRepository.putEmail(signIn.email)
        prefRepository.putPassword(signIn.password)
    }

}