package com.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.myapplication.PlaylistListContent
import com.myapplication.PlaylistViewModel

@Composable
fun PlaylistListView(
    playlistViewModel: PlaylistViewModel,
    navController: NavController,
    userId: String
){
    PlaylistListContent(playlistViewModel, navController, userId)
}

@Preview
@Composable
fun PlaylistListView(){
//    PlaylistView()
}
