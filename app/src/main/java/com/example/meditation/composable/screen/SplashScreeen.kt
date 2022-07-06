package com.example.meditation.composable.screen

import android.annotation.SuppressLint
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.meditation.R
import com.example.meditation.navigation.NavConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .size(202.dp, 213.dp),
            painter = painterResource(id = R.drawable.logo_foreground),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

    scope.launch {
        delay(1500)
        navController.navigate(NavConstants.onboarding) {
            popUpTo(NavConstants.splash) {
                inclusive = true
            }
        }
    }
}