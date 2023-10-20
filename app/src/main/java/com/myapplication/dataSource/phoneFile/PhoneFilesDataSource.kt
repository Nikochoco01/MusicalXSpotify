package com.myapplication.dataSource.phoneFile

import com.myapplication.model.Musics
import com.myapplication.model.Playlists

object PhoneFilesDataSource {
    private val musics = arrayListOf(
        Musics(1, "", "music1", "1er", "Android", "", ""),
        Musics(2, "", "music2", "1er", "Android", "", ""),
        Musics(3, "", "music3", "1er", "Android", "", "")
    )
    private val playlists = Playlists(1, "", "Test playlist", musics)

    fun getPlaylist(): Playlists{
        return playlists
    }
}