package com.example.meditation.composable.screen.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditation.model.MainModel
import com.example.meditation.model.dto.Feeling
import com.example.meditation.model.dto.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainModel: MainModel
): ViewModel() {

    val feelings = MutableLiveData<List<Feeling>>()
    val quotes = MutableLiveData<List<Quote>>()

    fun getFeelings() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainModel.getFeelings()
            if (response.isSuccessful) {
                feelings.postValue(response.body()?.data)
            }
        }
    }

    fun getQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainModel.getQuotes()
            if (response.isSuccessful) {
                quotes.postValue(response.body()?.data)
            }
        }
    }
}