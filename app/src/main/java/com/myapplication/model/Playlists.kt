package com.myapplication.model

import com.myapplication.model.Musics

data class Playlists(val id: Int, val images: String, val name: String, val tracks: ArrayList<Musics>)
