package com.example.meditation.composable.screen.registration.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meditation.ui.theme.BackgroundColor
import com.example.meditation.ui.theme.appFontFamily

@Composable
fun RegistrationScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Text(
            text = "Тут будет\nрегистрация",
            fontFamily = appFontFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(start = 88.dp, top = 355.dp),
            color = Color.White
        )
    }
}