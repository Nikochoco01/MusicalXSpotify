package com.myapplication.model.playlists

import com.myapplication.model.musics.Musics

data class Playlists(val id: Int, val images: String, val name: String, val tracks: ArrayList<Musics>)
