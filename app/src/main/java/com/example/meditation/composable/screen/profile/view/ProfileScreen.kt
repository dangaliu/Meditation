package com.example.meditation.composable.screen.profile.view

import android.annotation.SuppressLint
import android.graphics.ImageDecoder
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.toMutableStateList
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
import com.example.meditation.navigation.NavConstants
import com.example.meditation.ui.theme.appFontFamily
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import java.time.LocalDateTime
import java.time.ZoneOffset

@SuppressLint("MutableCollectionMutableState", "NewApi")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {

    val context = LocalContext.current

    profileViewModel.getFiles(context)
    val files = profileViewModel.imageFiles.collectAsState().value

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                val bitmap = ImageDecoder.decodeBitmap(source)
                profileViewModel.saveToStorage(context, bitmap)
            }
        }
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 26.dp)
            .padding(top = 25.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        FlowRow(
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 15.dp,
            modifier = Modifier.fillMaxWidth(),
            mainAxisSize = SizeMode.Wrap,
            mainAxisAlignment = FlowMainAxisAlignment.Start,
            crossAxisAlignment = FlowCrossAxisAlignment.Center
        ) {
            files.forEachIndexed { index, file ->
                val fileName = file.path.split("/").last().toString()
                val epochSeconds =
                    fileName.split(".").toString().trim(']', '[').split(",").first().substring(6)
                Log.d("fileName", fileName)
                Log.d("epochSeconds", epochSeconds)
                val localTime = LocalDateTime.ofEpochSecond(
                    epochSeconds.toLong(),
                    0,
                    ZoneOffset.UTC
                )
                val hour = if (localTime.hour < 10) "0${localTime.hour}" else "${localTime.hour}"
                val minute =
                    if (localTime.minute < 10) "0${localTime.minute}" else "${localTime.minute}"

                GalleryImageComponent(
                    galleryImage = GalleryImage(
                        "${hour}:${minute}",
                        profileViewModel.bitmapFromFile(files[index])
                    ),
                    modifier = Modifier.size(169.dp, 110.dp),
                    onClick = {
                        navController.navigate("${NavConstants.photo}/$fileName")
                    }
                )
            }
            AddComponent(
                onClick = {
                    imagePicker.launch("image/*")
                },
                modifier = Modifier.size(169.dp, 110.dp)
            )
        }
    }
}


