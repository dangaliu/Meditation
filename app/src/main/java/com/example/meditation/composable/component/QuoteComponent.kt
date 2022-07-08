package com.example.meditation.composable.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.meditation.model.dto.Quote
import com.example.meditation.ui.theme.appFontFamily

@Composable
fun QuoteComponent(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(170.dp),
    quote: Quote,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        backgroundColor = Color(0xFFF7F3F0),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 30.dp, top = 22.dp, bottom = 22.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = quote.title,
                    fontFamily = appFontFamily,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF253334)
                )
                Text(
                    text = quote.description,
                    fontFamily = appFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    maxLines = 2,
                    modifier = Modifier.width(160.dp),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(16.dp))
                OutlinedButton(
                    onClick = onClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color(0xFF253334)
                    ),
                    modifier = Modifier
                        .size(138.dp, 39.dp)
                ) {
                    Text(
                        text = "подробнее",
                        fontFamily = appFontFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
            AsyncImage(
                model = quote.image,
                contentDescription = null,
                modifier = Modifier.size(166.dp, 111.dp)
            )
        }
    }
}