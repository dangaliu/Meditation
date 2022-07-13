package com.example.meditation.composable.screen.profile.view

import android.annotation.SuppressLint
import android.graphics.ImageDecoder
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.meditation.composable.component.AddComponent
import com.example.meditation.composable.component.GalleryImageComponent
import com.example.meditation.composable.screen.profile.viewmodel.ProfileViewModel
import com.example.meditation.model.dto.GalleryImage
import com.example.meditation.ui.theme.appFontFamily
import java.time.LocalDateTime

@SuppressLint("MutableCollectionMutableState", "NewApi")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {

    val context = LocalContext.current
    var localTime = LocalDateTime.now()

    val images = profileViewModel.images.observeAsState(arrayListOf()).value.toMutableStateList()

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                val bitmap = ImageDecoder.decodeBitmap(source)
                localTime = LocalDateTime.now()
                profileViewModel.addImage(
                    GalleryImage(
                        time = "${localTime.hour}:${localTime.minute}", bitmap = bitmap
                    )
                )
                Log.d("images", profileViewModel.images.value.toString())
                Log.d("images", images.toList().toString())
            }
        }
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 26.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(25.dp))
        AsyncImage(
            model = profileViewModel.getAvatar(),
            contentDescription = profileViewModel.getName(),
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = profileViewModel.getName(),
            fontSize = 35.sp,
            fontFamily = appFontFamily,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Spacer(Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(images.size) { index ->
                GalleryImageComponent(galleryImage = images[index])
            }
            item {
                AddComponent(
                    onClick = {
                        imagePicker.launch("image/*")
                    }
                )
            }
        }
    }
}


