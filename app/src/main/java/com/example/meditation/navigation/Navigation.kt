package com.example.meditation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meditation.composable.screen.onboarding.view.OnBoardingScreen
import com.example.meditation.composable.screen.sign_in.view.SignInScreen
import com.example.meditation.composable.screen.splash.view.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavConstants.splash
    ) {
        composable(route = NavConstants.splash) {
            SplashScreen(navController = navController)
        }
        composable(route = NavConstants.onboarding) {
            OnBoardingScreen(navController = navController)
        }
        composable(route = NavConstants.signIn) {
            SignInScreen(navController = navController)
        }
    }
}