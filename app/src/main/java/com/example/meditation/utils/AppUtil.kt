package com.example.meditation.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

object AppUtil {
    @Composable
    fun drawableToBitmap(drawable: Int): Bitmap {
        return BitmapFactory.decodeResource(LocalContext.current.resources, drawable)
    }
}