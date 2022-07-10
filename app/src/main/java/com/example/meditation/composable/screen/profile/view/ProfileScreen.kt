package com.example.meditation.composable.screen.profile.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.meditation.R
import com.example.meditation.composable.component.GalleryImageComponent
import com.example.meditation.model.dto.GalleryImage
import com.example.meditation.model.shared_preferences.PrefRepository
import com.example.meditation.ui.theme.appFontFamily
import com.example.meditation.utils.AppUtil.drawableToBitmap

@Composable
fun ProfileScreen(
    navController: NavHostController,
    prefRepository: PrefRepository
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 26.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(25.dp))
        AsyncImage(
            model = prefRepository.getAvatar() ?: "",
            contentDescription = prefRepository.getName() ?: "",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = prefRepository.getName() ?: "Default",
            fontSize = 35.sp,
            fontFamily = appFontFamily,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Spacer(Modifier.height(15.dp))
        val images = ArrayList<GalleryImage>()
        images.add(GalleryImage("11:00", drawableToBitmap(drawable = R.drawable.photo1)))
        images.add(GalleryImage("15:55", drawableToBitmap(drawable = R.drawable.photo2)))
        images.add(GalleryImage("19:11", drawableToBitmap(drawable = R.drawable.photo3)))
        images.add(GalleryImage("11:11", drawableToBitmap(drawable = R.drawable.photo4)))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(images.size) { index ->  
                GalleryImageComponent(galleryImage = images[index])
            }
        }
    }
}
