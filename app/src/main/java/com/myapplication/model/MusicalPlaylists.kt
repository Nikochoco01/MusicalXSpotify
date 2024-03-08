package com.myapplication.model

import com.google.gson.annotations.SerializedName

data class MusicalPlaylists(
	val id: Int,
	val images: String,
	val name: String,
	var tracks: List<Musics>
)

data class SpotifyPlaylist(
    @SerializedName("id")
    val id: String,
    val images: List<PictureSpotify>? = null,
    @SerializedName("name")
    val name: String,
    val tracks: SpotifyTracks
)

data class SpotifyTracks(
    @SerializedName("href")
    val href: String
)

data class SpotifyResultPlaylist(
    @SerializedName("items")
    val playlists: List<SpotifyPlaylist>
)