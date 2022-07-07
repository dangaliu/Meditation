package com.example.meditation.composable.screen.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meditation.composable.component.AppTopBar
import com.example.meditation.composable.screen.main.viewmodel.MainViewModel
import com.example.meditation.composable.screen.sign_in.viewmodel.SignInViewModel
import com.example.meditation.ui.theme.BackgroundColor
import com.example.meditation.ui.theme.appFontFamily

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    avatarRes: String = "",
    signInViewModel: SignInViewModel,
    mainViewModel: MainViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val avatarRes = signInViewModel.signInResponse.observeAsState().value?.avatar ?: ""
    val name = signInViewModel.signInResponse.observeAsState().value?.nickName ?: ""
    mainViewModel.getFeelings()
    val feelings = mainViewModel.feelings.observeAsState(listOf()).value
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = BackgroundColor,
        scaffoldState = scaffoldState,
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(55.dp))
                AppTopBar(
                    navController = navController,
                    isMain = true,
                    avatarRes = avatarRes
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = it.calculateBottomPadding() + 1.dp, start = 18.dp, end = 18.dp)
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(25.dp))
            Text(
                text = "С возвращением, $name",
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = appFontFamily,
                color = Color.White
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Каким ты себя ощущаешь сегодня?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = appFontFamily,
                color = Color.White.copy(0.7f)
            )
            Spacer(Modifier.height(23.dp))
            LazyRow(
                content = {
                    items(feelings.size) { index ->
                        val feeling = feelings[index]
                        FeelingComponent(feeling.image, feeling.title)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(17.dp)
            )
            Spacer(modifier = Modifier.height(23.dp))
        }
    }
}