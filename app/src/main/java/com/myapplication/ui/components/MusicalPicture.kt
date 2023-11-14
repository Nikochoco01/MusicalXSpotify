package com.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MusicPicture(
    drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(56.dp)
            .clip(RoundedCornerShape(24)),
        painter = painterResource(id = drawableResource),
        contentDescription = description,
    )
}

@Composable
fun PlaylistPicture(
    drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(128.dp),
        painter = painterResource(id = drawableResource),
        contentDescription = description
    )
}