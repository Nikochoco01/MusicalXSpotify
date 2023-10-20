package com.myapplication.model

data class Playlists(
    val id: Int,
    val images: String,
    val name: String,
    val tracks: List<Musics>)
