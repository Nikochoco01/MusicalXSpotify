package com.myapplication.model

data class MusicalPlaylists(
	val id: Int,
	val images: String,
	val name: String,
	var tracks: List<Musics>
)

data class SpotifyPlaylist(
    val id: Int,
    val images: String,
    val name: String,
    val tracks: String
)