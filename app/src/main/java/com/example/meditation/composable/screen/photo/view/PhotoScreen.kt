package com.example.meditation.composable.screen.photo.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.meditation.composable.screen.photo.viewmodel.PhotoViewModel
import com.example.meditation.navigation.NavConstants
import com.example.meditation.ui.theme.appFontFamily

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotoScreen(
    navController: NavHostController,
    photoViewModel: PhotoViewModel,
    fileName: String = ""
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val imagePath = photoViewModel.getFileFromName(fileName, context)
        var isDoubleTap by remember { mutableStateOf(false) }
        val scale = if (isDoubleTap) 2f else 1f
        Log.d("imageUri", imagePath)
        val dismissState = rememberDismissState()
        SwipeToDismiss(state = dismissState, background = {

        }) {
            if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
                navController.popBackStack(NavConstants.profile, false)
            } else if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                photoViewModel.deleteImage(fileName, context)
                navController.popBackStack(NavConstants.profile, false)
            }

            AsyncImage(
                modifier = Modifier
                    .padding(top = 219.dp)
                    .height(241.dp)
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                isDoubleTap = !isDoubleTap
                            }
                        )
                    }
                    .scale(scale),
                model = imagePath,
                contentDescription = fileName,
                contentScale = ContentScale.Crop
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 64.dp)
                .padding(bottom = 45.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "удалить",
                fontSize = 20.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            photoViewModel.deleteImage(fileName, context)
                            navController.popBackStack()
                        }
                    )
                    .weight(1f),
                textAlign = TextAlign.Start,
                color = Color.White
            )
            Text(
                text = "закрыть",
                fontSize = 20.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                    .weight(1f),
                textAlign = TextAlign.End,
                color = Color.White
            )
        }
    }
}