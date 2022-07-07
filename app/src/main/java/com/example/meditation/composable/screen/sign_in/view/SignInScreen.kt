package com.example.meditation.composable.screen.sign_in.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meditation.R
import com.example.meditation.composable.component.AppButton
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInViewModel
import com.example.meditation.model.dto.SignInBody
import com.example.meditation.model.dto.SignInResponse
import com.example.meditation.navigation.NavConstants
import com.example.meditation.ui.theme.BackgroundColor
import com.example.meditation.ui.theme.PlaceHolder
import com.example.meditation.ui.theme.appFontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavHostController,
    signInViewModel: SignInViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val signInResponse = signInViewModel.signInResponse.observeAsState(SignInResponse("", "", "", "", "")).value
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 27.dp)
                .matchParentSize()
        ) {
            Spacer(Modifier.height(90.dp))

            Image(
                modifier = Modifier
                    .size(43.dp, 49.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null
            )

            Spacer(Modifier.height(31.dp))

            Text(
                text = "Sign in",
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                color = Color.White,
                letterSpacing = 0.sp
            )

            Spacer(Modifier.height(79.dp))

            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    backgroundColor = BackgroundColor,
                    cursorColor = PlaceHolder,
                    placeholderColor = PlaceHolder,
                    unfocusedIndicatorColor = PlaceHolder,
                    focusedIndicatorColor = PlaceHolder
                ),
                placeholder = {
                    Text(
                        text = "Email",
                        fontFamily = appFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(15.dp))

            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    backgroundColor = BackgroundColor,
                    cursorColor = PlaceHolder,
                    placeholderColor = PlaceHolder,
                    unfocusedIndicatorColor = PlaceHolder,
                    focusedIndicatorColor = PlaceHolder
                ),
                placeholder = {
                    Text(
                        text = "Пароль",
                        fontFamily = appFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(45.dp))

            AppButton(
                text = "Sign in",
                letterSpacing = 2.sp,
                onClick = {
                    val body = SignInBody(email, password)
                    if (signInViewModel.isSignInFieldsValid(body)) {
                        signInViewModel.signIn(signInBody = body)
                        navController.navigate(NavConstants.main) {
                            popUpTo(NavConstants.onboarding) {
                                inclusive = true
                            }
                        }
                    } else {
                        Toast.makeText(context, "Введены некорректные данные", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            )

            Spacer(Modifier.height(23.dp))

            Text(
                text = "Register",
                fontSize = 20.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp,
                color = Color.White
            )

            Spacer(Modifier.height(23.dp))

            AppButton(
                text = "Профиль",
                letterSpacing = 2.sp
            )
        }
        Image(
            painter = painterResource(R.drawable.ic_uzor),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}