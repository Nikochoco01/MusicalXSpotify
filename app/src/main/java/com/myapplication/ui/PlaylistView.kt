package com.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.myapplication.MusicListContent
import com.myapplication.PlaylistViewModel

@Composable
fun PlaylistView(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel
){
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        MusicListContent(modifier, playlistViewModel)
    }
}

@Preview
@Composable
fun PlaylistPreview(){
//    PlaylistView()
}
