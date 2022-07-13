package com.example.meditation.utils

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.File

object AppUtil {
    @Composable
    fun drawableToBitmap(drawable: Int): Bitmap {
        return BitmapFactory.decodeResource(LocalContext.current.resources, drawable)
    }

    fun getUriFromDrawable(drawableName: String, context: Context): Uri {
        return Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${context.packageName}/drawable/$drawableName")
    }

    fun getUriFromAsset(fileName: String): Uri {
        return Uri.fromFile(File("//android_asset/$fileName"))
    }

    val imagesDirPath = "/data/data/com.example.meditation/app_imageDir/"
}