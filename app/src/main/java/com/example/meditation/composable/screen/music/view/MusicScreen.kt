package com.example.meditation.composable.screen.music.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.appFontFamily

@Composable
fun MusicScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Тут будет \n" +
                    "прослушивание",
            fontFamily = appFontFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}