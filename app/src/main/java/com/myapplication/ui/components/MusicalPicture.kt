package com.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter

@Composable
fun MusicalPicture(
    modifier: Modifier,
    drawableResource: Int,
    description: String
){
    Image(
        modifier = modifier,
        painter = painterResource(id = drawableResource),
        contentDescription = description,
    )
}


@Composable
fun MusicalPictureFromURL(
    modifier: Modifier,
    url: String,
    description: String
){
    Image(
        modifier = modifier,
        painter = rememberImagePainter(data = url),
        contentDescription = description,
    )
}