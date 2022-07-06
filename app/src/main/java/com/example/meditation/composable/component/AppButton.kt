package com.example.meditation.composable.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.ButtonColor
import com.example.meditation.ui.theme.appFontFamily

@Composable
fun AppButton(
    text: String = "Войти в аккаунт",
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(61.dp)
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = ButtonColor,
            contentColor = Color.White
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontFamily = appFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 25.sp,
            color = Color.White
        )
    }
}