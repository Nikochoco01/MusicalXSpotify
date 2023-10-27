package com.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun PlaylistListContent(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    selectedDestination: MutableState<String>
){
//    LaunchedEffect(Unit){
//        playlistViewModel.fetchPhoneFilePlaylist(1)
//    }
    Box(modifier = modifier.fillMaxSize()){
//        val gotLiveData by playlistViewModel.phoneFileLiveData.observeAsState(
//            initial = Playlists(0, "", "", emptyList())
//        )
//
//        if(gotLiveData == null){
//            Text(text = "Playlist Null")
//        }
//        else{
//            if(selectedDestination.value == MusicalRoute.REMOVE)
//                MusicListRemove(modifier,gotLiveData)
//            else
//                MusicList(modifier, gotLiveData)
//        }
    }
}