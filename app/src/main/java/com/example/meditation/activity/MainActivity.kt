package com.example.meditation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.meditation.composable.component.AppBottomNavigation
import com.example.meditation.composable.component.AppTopBar
import com.example.meditation.composable.screen.main.viewmodel.MainFactory
import com.example.meditation.composable.screen.main.viewmodel.MainViewModel
import com.example.meditation.composable.screen.profile.viewmodel.ProfileFactory
import com.example.meditation.composable.screen.profile.viewmodel.ProfileViewModel
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInFactory
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInViewModel
import com.example.meditation.composable.screen.splash.view.viewmodel.SplashFactory
import com.example.meditation.composable.screen.splash.view.viewmodel.SplashViewModel
import com.example.meditation.model.MainModel
import com.example.meditation.model.SignInModel
import com.example.meditation.model.shared_preferences.PrefRepository
import com.example.meditation.navigation.NavConstants
import com.example.meditation.navigation.Navigation
import com.example.meditation.ui.theme.BackgroundColor
import com.example.meditation.ui.theme.MeditationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationTheme {
                App()
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun App() {
        val systemUiController = rememberSystemUiController()
        systemUiController.setSystemBarsColor(Color.Red)
        val context = LocalContext.current
        val navController = rememberNavController()
        val prefRepository = PrefRepository(context)

        val signInModel = SignInModel()
        val signInFactory = SignInFactory(signInModel, prefRepository)
        val signInViewModel = ViewModelProvider(this, signInFactory)[SignInViewModel::class.java]

        val mainModel = MainModel()
        val mainFactory = MainFactory(mainModel)
        val mainViewModel = ViewModelProvider(this, mainFactory)[MainViewModel::class.java]

        val splashFactory = SplashFactory(prefRepository)
        val splashViewModel = ViewModelProvider(this, splashFactory)[SplashViewModel::class.java]

        val profileFactory = ProfileFactory(prefRepository = prefRepository)
        val profileViewModel = ViewModelProvider(this, profileFactory)[ProfileViewModel::class.java]

        val bottomItems = listOf(
            "main", "music", "profile"
        )

        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (currentRoute in bottomItems) {
                    Column {
                        AppBottomNavigation(navController = navController)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            },
            backgroundColor = BackgroundColor,
            topBar = {
                if (currentRoute in bottomItems) {
                    Column {
                        Spacer(modifier = Modifier.height(56.dp))
                        AppTopBar(
                            navController = navController,
                            avatarRes = if (prefRepository.getAvatar().isNullOrEmpty()) {
                                signInViewModel.signInResponse.observeAsState().value?.avatar ?: ""
                            } else {
                                prefRepository.getAvatar()
                            } ?: "",
                            isMain = currentRoute != NavConstants.profile
                        )
                    }
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = it.calculateBottomPadding())
                    .fillMaxSize()
            ) {
                Navigation(
                    navController = navController,
                    signInViewModel = signInViewModel,
                    mainViewModel = mainViewModel,
                    splashViewModel = splashViewModel,
                    prefRepository = prefRepository,
                    profileViewModel = profileViewModel
                )
            }
        }
    }
}
