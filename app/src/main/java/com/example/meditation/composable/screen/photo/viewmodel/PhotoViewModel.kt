package com.example.meditation.composable.screen.photo.viewmodel

import android.content.Context
import android.content.ContextWrapper
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import java.io.File

class PhotoViewModel : ViewModel() {

    fun getFileFromName(fileName: String, context: Context): String {
        val cw = ContextWrapper(context)
        val file = File(cw.getDir("imageDir", Context.MODE_PRIVATE), fileName)
        return if (file.exists()) {
            file.toUri().toString()
        } else ""
    }

    fun deleteImage(fileName: String, context: Context): Boolean {
        val cw = ContextWrapper(context)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val file = File(directory, fileName)
        return if (file.exists()) {
            file.delete()
        } else {
            false
        }
    }
}