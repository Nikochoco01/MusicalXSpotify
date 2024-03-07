package com.myapplication.model

import com.google.gson.annotations.SerializedName

data class MusicalPlaylists(
    val id: String,
    val images: String,
    val name: String,
    val tracks: List<Musics>
)

data class SpotifyPlaylist(
    @SerializedName("id")
    val id: String,
    val images: List<PictureSpotify>? = null,
    @SerializedName("name")
    val name: String,
    val tracks: SpotifyTracks
)

data class PictureSpotify(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class SpotifyTracks(
    @SerializedName("href")
    val href: String
)

data class SpotifyResultPlaylist(
    @SerializedName("items")
    val playlists: List<SpotifyPlaylist>
)