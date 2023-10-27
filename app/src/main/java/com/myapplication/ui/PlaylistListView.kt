package com.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myapplication.MusicListContent
import com.myapplication.PlaylistViewModel

@Composable
fun PlaylistListView(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    selectedDestination: MutableState<String>
){
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        PlaylistListView(modifier, playlistViewModel, selectedDestination)
    }
}

@Preview
@Composable
fun PlaylistListView(){
//    PlaylistView()
}
