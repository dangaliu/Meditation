package com.example.meditation.composable.screen.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.meditation.composable.component.AppBottomNavigation
import com.example.meditation.composable.component.AppTopBar
import com.example.meditation.composable.screen.main.viewmodel.MainViewModel
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInViewModel
import com.example.meditation.navigation.BottomNavigation
import com.example.meditation.navigation.Navigation
import com.example.meditation.ui.theme.BackgroundColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    avatarRes: String = "",
    mainViewModel: MainViewModel,
    signInViewModel: SignInViewModel,
    innerNavController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = BackgroundColor,
        scaffoldState = scaffoldState,
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(55.dp))
                AppTopBar(
                    navController = innerNavController,
                    isMain = true,
                    avatarRes = avatarRes
                )
            }
        },
        bottomBar = {
            AppBottomNavigation(navController = innerNavController)
        }
    ) {
        Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding() + 1.dp)) {
            BottomNavigation(
                navController = innerNavController,
                signInViewModel = signInViewModel,
                mainViewModel = mainViewModel
            )
        }
    }
}