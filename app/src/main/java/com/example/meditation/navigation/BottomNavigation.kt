package com.example.meditation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meditation.composable.screen.home.view.HomeScreen
import com.example.meditation.composable.screen.main.viewmodel.MainViewModel
import com.example.meditation.composable.screen.music.view.MusicScreen
import com.example.meditation.composable.screen.profile.view.ProfileScreen
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInViewModel

@Composable
fun BottomNavigation(
    navController: NavHostController,
    signInViewModel: SignInViewModel,
    mainViewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = NavConstants.home) {
        composable(route = NavConstants.home) {
            HomeScreen(
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