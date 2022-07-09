package com.example.meditation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meditation.composable.screen.main.view.MainScreen
import com.example.meditation.composable.screen.main.viewmodel.MainViewModel
import com.example.meditation.composable.screen.music.view.MusicScreen
import com.example.meditation.composable.screen.onboarding.view.OnBoardingScreen
import com.example.meditation.composable.screen.onboarding.viewmodel.OnBoardingViewModel
import com.example.meditation.composable.screen.profile.view.ProfileScreen
import com.example.meditation.composable.screen.sign_in.view.SignInScreen
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInViewModel
import com.example.meditation.composable.screen.splash.view.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
    signInViewModel: SignInViewModel,
    mainViewModel: MainViewModel,
    onBoardingViewModel: OnBoardingViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavConstants.splash
    ) {
        composable(route = NavConstants.splash) {
            SplashScreen(navController = navController)
        }
        composable(route = NavConstants.onboarding) {
            OnBoardingScreen(
                navController = navController,
                onBoardingViewModel
            )
        }
        composable(route = NavConstants.signIn) {
            SignInScreen(
                navController = navController,
                signInViewModel = signInViewModel
            )
        }
        composable(route = NavConstants.main) {
            MainScreen(
                navController = navController,
                mainViewModel = mainViewModel,
                signInViewModel = signInViewModel
            )
        }
        composable(route = NavConstants.music) {
            MusicScreen()
        }
        composable(route = NavConstants.profile) {
            ProfileScreen()
        }
    }
}