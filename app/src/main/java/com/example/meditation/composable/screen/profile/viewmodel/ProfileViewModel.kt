package com.example.meditation.composable.screen.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meditation.model.dto.GalleryImage
import com.example.meditation.model.shared_preferences.PrefRepository

class ProfileViewModel(
    private val prefRepository: PrefRepository
) : ViewModel() {

    private val imagesMutable = MutableLiveData<MutableList<GalleryImage>>(mutableListOf())
    val images: LiveData<MutableList<GalleryImage>> = imagesMutable

    fun getSavedImages(): List<GalleryImage> {
        return prefRepository.getList()
    }


    fun saveImages(images: List<GalleryImage>) {
        prefRepository.saveList(images)
    }

    fun addImage(image: GalleryImage) {
       imagesMutable.value?.add(image)
    }

    fun getAvatar(): String {
        return prefRepository.getAvatar() ?: ""
    }

    fun getName(): String {
        return prefRepository.getName() ?: ""
    }

}