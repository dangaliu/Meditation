package com.example.meditation.composable.screen.onboarding.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meditation.R
import com.example.meditation.composable.component.AppButton
import com.example.meditation.navigation.NavConstants
import com.example.meditation.ui.theme.appFontFamily

@Composable
fun OnBoardingScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 27.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(180.dp))
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(191.dp, 199.dp),
            )
            Spacer(Modifier.height(32.dp))
            Text(
                text = "ПРИВЕТ",
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                color = Color.White,
                letterSpacing = 0.sp
            )
            Text(
                text = "Наслаждайся отборочными.\nБудь внимателен.\n" +
                        "Делай хорошо.",
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                letterSpacing = 0.sp
            )
            Spacer(modifier = Modifier.height(95.dp))
            AppButton(
                onClick = {
                    navController.navigate(NavConstants.signIn)
                }
            )
            Spacer(Modifier.height(18.dp))
            Text(
                text = AnnotatedString(
                    text = "Еще нет аккаунта?",
                    spanStyle = SpanStyle(
                        fontFamily = appFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        letterSpacing = 0.sp
                    )
                ).plus(
                    AnnotatedString(
                        text = " Зарегистрируйтесь",
                        spanStyle = SpanStyle(
                            fontFamily = appFontFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            letterSpacing = 0.sp
                        )
                    )
                ),
                modifier = Modifier.clickable {

                }
            )
        }
    }
}