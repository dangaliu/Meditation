package com.example.meditation.composable.screen.sign_in.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meditation.model.SignInModel
import com.example.meditation.model.dto.SignInBody
import com.example.meditation.model.dto.SignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel(
    private val signInModel: SignInModel
): ViewModel() {

    val signInResponse = MutableLiveData<SignInResponse>()

    fun signIn(signInBody: SignInBody) {
        signInModel.signIn(signInBody).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(
                call: Call<SignInResponse>,
                response: Response<SignInResponse>
            ) {
                if (response.body() != null) {
                    Log.d("signIn", response.code().toString())
                    signInResponse.value = response.body()
                } else {
                    Log.d("signIn", response.code().toString())
                }
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                Log.d("signIn", t.message.toString())
            }

        })
    }

    fun isSignInFieldsValid(signInBody: SignInBody): Boolean {
        return !(signInBody.email.isEmpty() || signInBody.password.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(signInBody.email).matches())
    }
}