package com.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapplication.model.MusicalPlaylists
import com.myapplication.ui.components.MusicListItem
import com.myapplication.ui.components.MusicListItemSelected

@Composable
fun MusicListContent(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    selectedDestination: MutableState<String>
){
    LaunchedEffect(Unit){
        playlistViewModel.fetchPhoneFilePlaylist(1)
    }
    Box(modifier = modifier.fillMaxSize()){
        val gotLiveData by playlistViewModel.phoneFileLiveData.observeAsState(
                    initial = MusicalPlaylists(0, "", "", emptyList()))

        if(gotLiveData == null){
            AlertDialog(
                title = {
                        Text(text = "No playlist")
                },
                text = {
                       Text(text = "You haven't any playlist")
                },
                onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }
            )
        }
        else{
            if(selectedDestination.value == MusicalRoute.REMOVE_MUSICS)
                MusicListRemove(modifier,gotLiveData)
            else
                MusicList(modifier, gotLiveData)
        }
    }
}

@Composable
fun MusicList(
    modifier: Modifier,
    playlist : MusicalPlaylists
){
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        items(items = playlist.tracks, key = { it.id }){
            music -> MusicListItem(music = music)
        }
    }
}

@Composable
fun MusicListRemove(
    modifier: Modifier,
    playlist : MusicalPlaylists
){
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        items(items = playlist.tracks, key = { it.id }){
                music -> MusicListItemSelected(music = music)
        }
    }
}