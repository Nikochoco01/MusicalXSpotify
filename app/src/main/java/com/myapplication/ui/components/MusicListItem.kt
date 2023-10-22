package com.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.R
import com.myapplication.model.Musics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicListItem(
    music: Musics,
    modifier: Modifier = Modifier
){
    ListItem(
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        leadingContent = {
            MusicPicture(drawableResource = R.drawable.picture_1, description = "music picture")
        },
        headlineText = {
            Text(text = music.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp,
                    fontWeight = FontWeight(500),
                )
            )
        },
        supportingText = {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Text(
                    text = music.artist,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
//                    fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colorScheme.onSurface,
                        letterSpacing = 0.25.sp,
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
        }
    )
}
