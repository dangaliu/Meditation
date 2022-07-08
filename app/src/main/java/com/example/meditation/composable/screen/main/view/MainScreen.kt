package com.example.meditation.composable.screen.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.meditation.composable.component.QuoteComponent
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
    mainViewModel.getQuotes()
    val quotes = mainViewModel.quotes.observeAsState(listOf()).value
    val scrollState = rememberScrollState()
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
                .padding(bottom = it.calculateBottomPadding() + 1.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(Modifier.height(25.dp))
            Text(
                text = "С возвращением, $name",
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = appFontFamily,
                color = Color.White,
                modifier = Modifier.padding(start = 25.dp)
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Каким ты себя ощущаешь сегодня?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = appFontFamily,
                color = Color.White.copy(0.7f),
                modifier = Modifier.padding(start = 25.dp)
            )
            Spacer(Modifier.height(23.dp))
            LazyRow(
                content = {
                    items(feelings.size) { index ->
                        val feeling = feelings[index]
                        FeelingComponent(feeling.image, feeling.title)
                    }
                },
                modifier = Modifier
                    .padding(start = 25.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(17.dp)
            )
            Spacer(modifier = Modifier.height(17.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxSize()
            ) {
                for (quote in quotes) {
                    QuoteComponent(quote = quote)
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }
    }
}