package com.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.myapplication.ui.components.PlaylistListItem
import com.myapplication.ui.components.PlaylistListItemSelected

@Composable
fun PlaylistListContent(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    selectedDestination: MutableState<String>
){
    LaunchedEffect(Unit){
        playlistViewModel.fetchAllPlaylists("")
    }
    Box(modifier = modifier.fillMaxSize()){
        val gotLiveData by playlistViewModel.playlistsLiveData.observeAsState(
            initial = emptyList()
        )

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
            if(selectedDestination.value == MusicalRoute.REMOVE_PLAYLIST)
                PlaylistsListRemove(modifier,gotLiveData)
            else
                PlaylistsList(modifier, gotLiveData)
        }
    }
}

@Composable
fun PlaylistsList(
    modifier: Modifier,
    playlists : List<MusicalPlaylists>
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)){
        items(playlists){
            playlist -> PlaylistListItem(playlist = playlist)
        }
    }
}

@Composable
fun PlaylistsListRemove(
    modifier: Modifier,
    playlists : List<MusicalPlaylists>
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)){
        items(playlists){
                playlist -> PlaylistListItemSelected(playlist = playlist)
        }
    }
}