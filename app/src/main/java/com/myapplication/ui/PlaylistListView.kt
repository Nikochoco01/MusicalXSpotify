package com.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.myapplication.MusicListContent
import com.myapplication.PlaylistListContent
import com.myapplication.PlaylistViewModel

@Composable
fun PlaylistListView(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    navController: NavController,
    userId: String
){
    Column(
        modifier = modifier
            .background(Color.Green)
    ) {
        PlaylistListContent(modifier, playlistViewModel, navController, userId)
    }
}

@Preview
@Composable
fun PlaylistListView(){
//    PlaylistView()
}
