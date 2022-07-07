package com.example.meditation.composable.screen.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditation.model.MainModel

class MainFactory(private val mainModel: MainModel): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mainModel = mainModel) as T
    }
}