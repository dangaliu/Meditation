package com.example.meditation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.meditation.composable.screen.main.view.MainScreen
import com.example.meditation.composable.screen.main.viewmodel.MainViewModel
import com.example.meditation.composable.screen.menu.view.MenuScreen
import com.example.meditation.composable.screen.music.view.MusicScreen
import com.example.meditation.composable.screen.onboarding.view.OnBoardingScreen
import com.example.meditation.composable.screen.photo.view.PhotoScreen
import com.example.meditation.composable.screen.photo.viewmodel.PhotoViewModel
import com.example.meditation.composable.screen.profile.view.ProfileScreen
import com.example.meditation.composable.screen.profile.viewmodel.ProfileViewModel
import com.example.meditation.composable.screen.registration.view.RegistrationScreen
import com.example.meditation.composable.screen.sign_in.view.SignInScreen
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInViewModel
import com.example.meditation.composable.screen.splash.view.SplashScreen
import com.example.meditation.composable.screen.splash.viewmodel.SplashViewModel
import com.example.meditation.model.shared_preferences.PrefRepository

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navController: NavHostController,
    signInViewModel: SignInViewModel,
    mainViewModel: MainViewModel,
    splashViewModel: SplashViewModel,
    prefRepository: PrefRepository,
    profileViewModel: ProfileViewModel,
    photoViewModel: PhotoViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavConstants.splash
    ) {
        composable(route = NavConstants.splash) {
            SplashScreen(navController = navController, splashViewModel = splashViewModel)
        }
        composable(route = NavConstants.onboarding) {
            OnBoardingScreen(
                navController = navController
            )
        }
        composable(route = NavConstants.signIn) {
            SignInScreen(
                navController = navController,
                signInViewModel = signInViewModel,
                prefRepository = prefRepository
            )
        }
        composable(route = NavConstants.registration) {
            RegistrationScreen(navController = navController)
        }
        composable(route = NavConstants.menu) {
            MenuScreen(navController = navController)
        }
        composable(route = NavConstants.main) {
            MainScreen(
                navController = navController,
                mainViewModel = mainViewModel,
                signInViewModel = signInViewModel,
                prefRepository = prefRepository
            )
        }
        composable(
            route = "${NavConstants.photo}/{${NavConstants.fileName}}",
            arguments = listOf(
                navArgument(name = NavConstants.fileName) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            PhotoScreen(
                navController = navController,
                photoViewModel = photoViewModel,
                fileName = navBackStackEntry.arguments?.getString(NavConstants.fileName, "") ?: ""
            )
        }
        composable(route = NavConstants.music) {
            MusicScreen()
        }
        composable(route = NavConstants.profile) {
            ProfileScreen(
                navController = navController,
                profileViewModel = profileViewModel
            )
        }
    }
}