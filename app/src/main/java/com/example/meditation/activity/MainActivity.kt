package com.example.meditation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.meditation.composable.screen.main.viewmodel.MainFactory
import com.example.meditation.composable.screen.main.viewmodel.MainViewModel
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInFactory
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInViewModel
import com.example.meditation.model.MainModel
import com.example.meditation.model.SignInModel
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
        val scaffoldState = rememberScaffoldState()

        val signInModel = SignInModel()
        val signInFactory = SignInFactory(signInModel)
        val signInViewModel = ViewModelProvider(this, signInFactory)[SignInViewModel::class.java]

        val mainModel = MainModel()
        val mainFactory = MainFactory(mainModel)
        val mainViewModel = ViewModelProvider(this, mainFactory)[MainViewModel::class.java]

        val innerNavController = rememberNavController()
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Navigation(
                navController = navController,
                signInViewModel = signInViewModel,
                mainViewModel = mainViewModel,
                innerNavController = innerNavController
            )
        }
    }
}
