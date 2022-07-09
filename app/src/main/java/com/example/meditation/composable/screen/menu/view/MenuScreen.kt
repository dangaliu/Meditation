package com.example.meditation.composable.screen.menu.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meditation.ui.theme.BackgroundColor
import com.example.meditation.ui.theme.appFontFamily

@Composable
fun MenuScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Тут будет меню",
            fontFamily = appFontFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(top = 355.dp),
            color = Color.White
        )
    }
}