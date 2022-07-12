package com.example.meditation.composable.screen.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meditation.model.dto.GalleryImage
import com.example.meditation.model.shared_preferences.PrefRepository
import com.example.meditation.utils.AppUtil

class ProfileViewModel(
    private val prefRepository: PrefRepository
) : ViewModel() {

    val images = MutableLiveData<ArrayList<GalleryImage>>()

    fun getSavedImages(): List<GalleryImage> {
        return prefRepository.getList()
    }


    fun saveImages() {
        images.value?.let { prefRepository.saveList(it) }
    }

    fun addImage(image: GalleryImage) {
        images.value?.add(image)
    }


    fun getAvatar(): String {
        return prefRepository.getAvatar() ?: ""
    }

    fun getName(): String {
        return prefRepository.getName() ?: ""
    }

}