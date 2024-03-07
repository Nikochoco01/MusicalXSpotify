package com.myapplication.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.model.MusicalPlaylists
import com.myapplication.navigation.MusicalRoute
import com.myapplication.ui.components.MusicListItem
import com.myapplication.ui.components.MusicListItemSelected

@SuppressLint("SuspiciousIndentation")
@Composable
fun MusicListView(
    playlistViewModel: PlaylistViewModel,
    navController: NavController,
    playlistId: Int
){
    LaunchedEffect(Unit){
        playlistViewModel.fetchPlaylistByID(playlistId)
    }

    val gotLiveData by playlistViewModel.playlistPhoneById.observeAsState(
        initial = MusicalPlaylists(0, "", "", emptyList()))

        if(gotLiveData == null){
            AlertDialog(
                title = { Text(text = "No playlist") },
                text = { Text(text = "You haven't any playlist") },
                onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }
            )
        }
        else{
            if(navController.currentBackStackEntry?.destination?.route == MusicalRoute.MUSICS_REMOVE)
                MusicListRemove(gotLiveData)
            else{
                MusicList(gotLiveData)
            }
        }
}

@Composable
fun MusicList(
    playlist : MusicalPlaylists
){
    LazyColumn(
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        items(items = playlist.tracks, key = { it.id }){
            music -> MusicListItem(music = music)
        }
    }
}

@Composable
fun MusicListRemove(
    playlist : MusicalPlaylists
){
    LazyColumn(
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        items(items = playlist.tracks, key = { it.id }){
                music -> MusicListItemSelected(music = music)
        }
    }
}