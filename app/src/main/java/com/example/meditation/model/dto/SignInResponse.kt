package com.example.meditation.model.dto

data class SignInResponse(
    val id: String,
    val email: String,
    val nickName: String,
    val avatar: String,
    val token: String
)
