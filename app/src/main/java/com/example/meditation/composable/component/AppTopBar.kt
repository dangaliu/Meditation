package com.example.meditation.composable.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.meditation.R
import com.example.meditation.ui.theme.appFontFamily

@Composable
fun AppTopBar(
    isMain: Boolean = true,
    avatarRes: String = "",
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(23.dp, 18.dp),
            painter = painterResource(R.drawable.ic_hamburger),
            contentDescription = null,
        )

        Image(
            modifier = Modifier
                .size(45.dp, 49.dp),
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = null
        )

        if(isMain) {
            AsyncImage(
                model = avatarRes,
                modifier = Modifier.size(36.dp).clip(CircleShape),
                contentDescription = null
            )
        } else {
            Text(
                text = "exit",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = appFontFamily
            )
        }
    }
}