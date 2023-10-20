package com.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.myapplication.model.Playlists
import com.myapplication.ui.components.MusicListItem

@Composable
fun MusicListContent(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel
){
    Box(modifier = modifier.fillMaxSize()){
        playlistViewModel.fetchPhoneFilePlaylist(1)
        val gotLiveData by playlistViewModel.phoneFileLiveData.observeAsState(
                    initial = Playlists(0, "", "", emptyList()))

        if(gotLiveData == null){
            Text(text = "Playlist Null")
        }
        else{
            MusicList(modifier, gotLiveData)
        }
    }
}

@Composable
fun MusicList(
    modifier: Modifier,
    playlist : Playlists
){
    LazyColumn(modifier = modifier){
        items(items = playlist.tracks, key = { it.id }){
            music -> MusicListItem(music = music)
        }
    }
}