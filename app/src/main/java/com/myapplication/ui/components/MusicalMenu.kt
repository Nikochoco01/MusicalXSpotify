package com.myapplication.ui.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.myapplication.model.SpotifyPlaylist

@Composable
fun MusicalMenu(
	expanded: MutableState<Boolean>,
	onDismiss: () -> Unit,
	removePlaylist: () -> Unit,
	importPlaylist: () -> Unit,
	findSpotifyPlaylist: () -> Unit
){
	DropdownMenu(
		expanded = expanded.value,
		onDismissRequest = { onDismiss.invoke()},
		content = {
			DropdownMenuItem(text = { Text(text = "Import playlist")}, onClick = { importPlaylist.invoke() })
			DropdownMenuItem(text = { Text(text = "Import Spotify playlist")}, onClick = { findSpotifyPlaylist.invoke() })
			DropdownMenuItem(text = { Text(text = "Remove playlist")}, onClick = { removePlaylist.invoke() })
		}
	)
}