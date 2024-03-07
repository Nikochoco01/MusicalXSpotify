package com.myapplication.ui.views


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.myapplication.ui.components.PlaylistListItem
import com.myapplication.ui.components.PlaylistListItemSelected

@Composable
fun PlaylistListView(
    playlistViewModel: PlaylistViewModel,
    navController: NavController,
    userId: String
){
    LaunchedEffect(Unit){
        playlistViewModel.fetchAllPlaylists(userId)
        playlistViewModel.fetchAllSpotifyPlaylist(
            "31noc2ncy5jd6vj6ylnbx5xddgcu",
            "BQDQ0VxuntUVY8Nfrz7QTsbYuOWVRbqmrcxZOHEQGDsBnOub0PG7b2UnBhAqNIWzqbp52ydw_leAe4U2mgjtkhhDzxy2a9evA_5IbAVyLZF1uGp1rMA")
    }
    val gotLiveData by playlistViewModel.playlistsLiveData.observeAsState(
        initial = emptyList()
    )

    val spotifyPlaylist by playlistViewModel.spotifyResultPlaylists.observeAsState()

    Log.e("error", "playlists spotify ${spotifyPlaylist?.playlists}")

    if(gotLiveData == null){
        AlertDialog(
            title = { Text(text = "No playlist") },
            text = { Text(text = "You haven't any playlist") },
            onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }
        )
    }
    else{
        if(navController.currentBackStackEntry?.destination?.route == MusicalRoute.PLAYLIST_REMOVE)
            PlaylistsListRemove(navController,gotLiveData)
        else
            PlaylistsList(navController, gotLiveData)
    }
}

@Composable
fun PlaylistsList(
    navController: NavController,
    playlists : List<MusicalPlaylists>
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)){
        items(playlists){
            playlist -> PlaylistListItem(playlist = playlist, navController)
        }
    }
}

@Composable
fun PlaylistsListRemove(
    navController: NavController,
    playlists : List<MusicalPlaylists>
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)){
        items(playlists){
                playlist -> PlaylistListItemSelected(playlist = playlist)
        }
    }
}