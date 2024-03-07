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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.model.MusicalPlaylists
import com.myapplication.navigation.MusicalRoute
import com.myapplication.ui.components.PlaylistListItem
import com.myapplication.ui.components.PlaylistListItemSelected
import com.myapplication.viewModels.PhoneManagerViewModel

@Composable
fun PlaylistListView(
    phoneManagerViewModel: PhoneManagerViewModel,
    playlistViewModel: PlaylistViewModel,
    navController: NavController,
    userId: String
){
    LaunchedEffect(Unit){
        playlistViewModel.fetchAllPlaylists(userId)
    }

    var context = LocalContext.current
    val permissionRecovered by phoneManagerViewModel.initPermission.observeAsState()
    val selectedDirectory by phoneManagerViewModel.phoneFileRecovered.observeAsState()
    val playlistIsCreate by playlistViewModel.createPlaylistLiveData.observeAsState(initial = false)
    val allPlaylists by playlistViewModel.allPhonePlaylists.observeAsState(
        initial = emptyList()
    )
    var oldPermission by remember{ mutableStateOf(permissionRecovered) }
    var oldSelectedDirectory by remember{ mutableStateOf(selectedDirectory) }
    var oldPlaylistIsCreate by remember{ mutableStateOf(playlistIsCreate) }
    var oldAllPlaylists by remember{ mutableStateOf(allPlaylists) }

    Log.e("error", "Permission: $permissionRecovered")
    Log.e("error", "File recovered: $selectedDirectory")
    Log.e("error", "Playlist is create: $playlistIsCreate")
    Log.e("error", "All playlist loaded: $allPlaylists")

    Log.e("error", "remb Permission: $oldPermission")
    Log.e("error", "remb File recovered: $selectedDirectory")
    Log.e("error", "remb Playlist is create: $oldPlaylistIsCreate")
    Log.e("error", "remb All playlist loaded: $oldAllPlaylists")

//    if(permissionGot){
//        Log.e("error", "Permission allowed")
//    }

//    LaunchedEffect(Unit){
//        phoneManagerViewModel.initPermission()
//        if(oldSelectedDirectory != selectedDirectory){
//            phoneManagerViewModel.getPhoneFile()
//            oldSelectedDirectory = selectedDirectory
//        }
//    }

    if(oldSelectedDirectory != selectedDirectory){
        playlistViewModel.createPlaylist(context, selectedDirectory!!)
        Log.e("error", "SELECT playlist create $playlistIsCreate")
    }

    if(playlistIsCreate){
        Log.e("error", "IS CREATE all playlist $allPlaylists")
        playlistViewModel.fetchAllPlaylists(userId)
    }

    if(allPlaylists == null){
        AlertDialog(
            title = { Text(text = "No playlist") },
            text = { Text(text = "You haven't any playlist") },
            onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }
        )
    }
    else{
        if(navController.currentBackStackEntry?.destination?.route == MusicalRoute.PLAYLIST_REMOVE)
            PlaylistsListRemove(allPlaylists)
        else
            PlaylistsList(navController, allPlaylists)
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