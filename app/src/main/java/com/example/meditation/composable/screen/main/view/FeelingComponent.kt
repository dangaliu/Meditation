package com.example.meditation.composable.screen.main.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.meditation.ui.theme.appFontFamily

@Composable
fun FeelingComponent(
    image: String = "",
    title: String = ""
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(62.dp, 65.dp),
            color = Color.White.copy(0.9f),
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }
        }

        Spacer(Modifier.height(5.dp))

        Text(
            text = title,
            fontSize = 12.sp,
            fontFamily = appFontFamily,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }
}