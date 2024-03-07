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
import com.myapplication.model.SpotifyPlaylist
import com.myapplication.navigation.MusicalRoute
import com.myapplication.ui.components.PlaylistListItem
import com.myapplication.ui.components.PlaylistListItemSelected
import com.myapplication.ui.components.PlaylistSpotifyListItem
import com.myapplication.viewModels.PhoneManagerViewModel

@Composable
fun PlaylistSpotifyView(
	playlistViewModel: PlaylistViewModel,
	navController: NavController,
	userId: String
){
	LaunchedEffect(Unit){
		playlistViewModel.fetchAllSpotifyPlaylist(
			"31noc2ncy5jd6vj6ylnbx5xddgcu",
			"BQBKXQtwFjTNLMd29G8ZAlRc2zZVYALXBWbPpqSR3xh-U0dhhrASYlctNu7lfrXNRwQEddaHquhjnt7XPg-xDpI0rTeV7lpdYqb1kwJznAZ_Dm6KTyA")
	}

	val spotifyPlaylist by playlistViewModel.spotifyResultPlaylists.observeAsState()

	Log.e("error", "playlists spotify ${spotifyPlaylist?.playlists}")

	if(spotifyPlaylist == null){
		AlertDialog(
			title = { Text(text = "No playlist") },
			text = { Text(text = "You haven't any playlist") },
			onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }
		)
	}
	else{
//		if(navController.currentBackStackEntry?.destination?.route == MusicalRoute.PLAYLIST_REMOVE)
//			PlaylistsSpotifyListRemove(allPlaylists)
//		else
		spotifyPlaylist?.playlists?.let { PlaylistsSpotifyList(navController, it) }
	}
}

@Composable
fun PlaylistsSpotifyList(
	navController: NavController,
	playlists : List<SpotifyPlaylist>
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
				playlist -> PlaylistSpotifyListItem(playlist = playlist, navController)
		}
	}
}

@Composable
fun PlaylistsSpotifyListRemove(
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