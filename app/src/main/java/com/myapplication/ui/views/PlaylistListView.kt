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
import com.myapplication.repository.bluetooth.BluetoothCodeTransmissions
import com.myapplication.ui.components.PlaylistListItem
import com.myapplication.ui.components.PlaylistListItemSelected
import com.myapplication.viewModels.MusicalControlViewModel

@Composable
fun PlaylistListView(
    playlistViewModel: PlaylistViewModel,
    musicalControlViewModel: MusicalControlViewModel,
    navController: NavController,
    userId: String,
    sendAllPlaylist: (List<MusicalPlaylists>) -> Unit,
    sendPlaylistById: (MusicalPlaylists) -> Unit
){
    LaunchedEffect(Unit){
        playlistViewModel.fetchAllPlaylists(userId)
    }
    val orderFromWatch by musicalControlViewModel.orderFromWatch.observeAsState()
    val playlistPhone by playlistViewModel.playlistsLiveData.observeAsState(
        initial = emptyList()
    )

    if(orderFromWatch == BluetoothCodeTransmissions.GET_ALL_PLAYLIST){
        Log.e("error", "Order From watch $orderFromWatch")
        if(!playlistPhone.isNullOrEmpty())
            sendAllPlaylist.invoke(playlistPhone)
        else
            sendAllPlaylist.invoke(emptyList())
    }

//    if(orderFromWatch == BluetoothCodeTransmissions.GET_PLAYLIST_BY_ID){
//        if(!gotLiveData.isNullOrEmpty())
//            sendPlaylistById.invoke(gotLiveData.)
//        else
//            sendAllPlaylist.invoke(emptyList())
//    }

    if(playlistPhone == null){
        AlertDialog(
            title = { Text(text = "No playlist") },
            text = { Text(text = "You haven't any playlist") },
            onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }
        )
    }
    else{
        if(navController.currentBackStackEntry?.destination?.route == MusicalRoute.PLAYLIST_REMOVE)
            PlaylistsListRemove(navController,playlistPhone)
        else
            PlaylistsList(navController, playlistPhone)
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