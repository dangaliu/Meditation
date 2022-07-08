package com.example.meditation.model.dto

data class QuoteResponse(
    val success: Boolean,
    val data: List<Quote>
)
