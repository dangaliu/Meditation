package com.example.meditation.composable.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.model.dto.GalleryImage
import com.example.meditation.ui.theme.appFontFamily

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GalleryImageComponent(
    modifier: Modifier = Modifier.size(110.dp, 110.dp),
    galleryImage: GalleryImage,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        onClick = { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (galleryImage.bitmap != null) {
                Image(
                    bitmap = galleryImage.bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = galleryImage.time,
                    fontSize = 18.sp,
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 17.dp, bottom = 18.dp)
                )
            }
        }
    }
}