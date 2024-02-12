package com.myapplication.model

import kotlinx.serialization.Serializable

@Serializable
data class MusicalPlaylists(
    val id: Int,
    val images: String,
    val name: String,
    val tracks: List<Musics>
)
@Serializable
data class SpotifyPlaylist(
    val id: Int,
    val images: String,
    val name: String,
    val tracks: String
)