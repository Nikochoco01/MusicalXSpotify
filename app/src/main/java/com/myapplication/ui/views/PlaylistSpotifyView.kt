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
			"BQDg7ivRCYUNEV_l1mx9hOBmweGKD9vUDli_Kba_v1yrLdTYvH3BzRcarKPBGxuohjodcR6Sy3K6zQ2m9fIKl_ZNH35_rDU9iay3NDAKeUhAc-GWD0E")
	}
	val spotifyPlaylist by playlistViewModel.spotifyResultPlaylists.observeAsState()

	if(spotifyPlaylist == null){
		AlertDialog(
			title = { Text(text = "No playlist") },
			text = { Text(text = "You haven't any playlist") },
			onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }
		)
	}
	else{
		spotifyPlaylist?.playlists?.let { PlaylistsSpotifyList(navController, playlistViewModel, it) }
	}
}

@Composable
fun PlaylistsSpotifyList(
	navController: NavController,
	playlistViewModel: PlaylistViewModel,
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
				playlistViewModel.fetchAllTracksFromPlaylist(playlist.id,
					"BQDg7ivRCYUNEV_l1mx9hOBmweGKD9vUDli_Kba_v1yrLdTYvH3BzRcarKPBGxuohjodcR6Sy3K6zQ2m9fIKl_ZNH35_rDU9iay3NDAKeUhAc-GWD0E")
				//var route: String = "${MusicalInternalAppRoute.LoadPlaylist.route}"
//					.replace("{playlistID}", "${playlist.id}")
//				navController.navigate(route)
			})
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