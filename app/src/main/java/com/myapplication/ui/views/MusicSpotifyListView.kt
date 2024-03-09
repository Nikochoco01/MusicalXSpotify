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
import com.myapplication.model.SpotifyResultTracks
import com.myapplication.ui.components.spotify.MusicSpotifyListItem
import com.myapplication.viewModels.PlaylistViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun MusicSpotifyListView(
	playlistViewModel: PlaylistViewModel,
	playlistId: String,
	token: String
){
	LaunchedEffect(Unit){
		playlistViewModel.fetchAllTracksFromPlaylist(playlistId, token)
	}

	val musicFromPlaylistSpotify by playlistViewModel.spotifyResultTracksPlaylist.observeAsState()

	if(musicFromPlaylistSpotify == null){
		AlertDialog(
			title = { Text(text = "No playlist") },
			text = { Text(text = "You haven't any playlist") },
			onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }
		)
	}
	else{
		MusicSpotifyList(musicFromPlaylistSpotify!!)
	}
}

@Composable
fun MusicSpotifyList(
	playlist : SpotifyResultTracks
){
	LazyColumn(
		contentPadding = PaddingValues(
			start = 12.dp,
			top = 16.dp,
			end = 12.dp,
			bottom = 16.dp
		),
		verticalArrangement = Arrangement.spacedBy(16.dp)){
		items(items = playlist.tracks){
				music -> MusicSpotifyListItem(music = music.spotifyMusic)
		}
	}
}