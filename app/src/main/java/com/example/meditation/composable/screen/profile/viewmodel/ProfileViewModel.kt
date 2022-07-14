package com.example.meditation.composable.screen.profile.viewmodel

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditation.model.dto.GalleryImage
import com.example.meditation.model.shared_preferences.PrefRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.ZoneOffset

class ProfileViewModel(
    private val prefRepository: PrefRepository
) : ViewModel() {

    private val imagesMutable = MutableLiveData<SnapshotStateList<GalleryImage>>()
    val images: LiveData<SnapshotStateList<GalleryImage>> = imagesMutable

    private val imageFilesMutable = MutableStateFlow<MutableList<File>>(mutableListOf())
    val imageFiles: StateFlow<MutableList<File>> = imageFilesMutable

    fun getSavedImages(): List<GalleryImage> {
        return prefRepository.getList()
    }


    fun saveImages(images: List<GalleryImage>) {
        prefRepository.saveList(images)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun saveToStorage(context: Context, bitmap: Bitmap) {
        val localTime = LocalDateTime.now()
        val cw = ContextWrapper(context)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val zoneOffset = ZoneOffset.UTC
        val file = File(directory, "photo_${localTime.toEpochSecond(zoneOffset)}.jpg")
        if (!file.exists()) {
            val fos: FileOutputStream?
            try {
                fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()
                imageFilesMutable.value.add(file)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getFiles(context: Context) {
        val cw = ContextWrapper(context)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        viewModelScope.launch {
            if (imageFilesMutable.value.size != directory.listFiles().size) {
                imageFilesMutable.emit(directory.listFiles().toMutableList().toMutableStateList())
            }
        }
    }

    fun bitmapFromFile(file: File): Bitmap {
        return BitmapFactory.decodeFile(file.path)
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