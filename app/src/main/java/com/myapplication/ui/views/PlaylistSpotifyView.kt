package com.myapplication.ui.views

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
import com.myapplication.model.SpotifyPlaylist
import com.myapplication.navigation.MusicalInternalAppRoute
import com.myapplication.ui.components.spotify.PlaylistSpotifyListItem

@Composable
fun PlaylistSpotifyView(
	playlistViewModel: PlaylistViewModel,
	navController: NavController,
	spotifyID: String,
	token: String
){
	LaunchedEffect(Unit){
		playlistViewModel.fetchAllSpotifyPlaylist(spotifyID, token)
	}

	val spotifyPlaylist by playlistViewModel.spotifyResultPlaylists.observeAsState()

	if(spotifyPlaylist?.playlists == null){
		AlertDialog(
			title = { Text(text = "No playlist") },
			text = {
				Text(text = "You haven't any playlist on your spotify account")
			},
			onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }
		)
	}
	else{
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
				playlist ->
			PlaylistSpotifyListItem(playlist = playlist, onSelectPlaylist = {
				var route: String = "${MusicalInternalAppRoute.LoadSpotifyMusics.route}"
					.replace("{playlistID}", "${playlist.id}")
				navController.navigate(route)
			})
		}
	}
}