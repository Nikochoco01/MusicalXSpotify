package com.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.myapplication.model.Musics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicListItem(
    music: Musics,
    modifier: Modifier = Modifier,
//    navigateToDetail: (Long) -> Unit
){
    ListItem(
        modifier = modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant),
//        leadingContent = ,
        headlineText = { Text(text = music.name) },
        supportingText = { 
            Text(
                text = music.artist,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
//                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colorScheme.onSurface,
                    letterSpacing = 0.5.sp,
                )
            )
            Text(
                text = music.releaseDate,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
//                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 0.25.sp,
                )
            )
        }
    )
}
