package com.myapplication.dataSource.phoneFile

import com.myapplication.model.Musics
import com.myapplication.model.MusicalPlaylists

object PhoneFilesDataSource {
    private val musics = arrayListOf(
        Musics(1, "", "music1", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(2, "", "music2", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(3, "", "music3", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(4, "", "music4", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(5, "", "music5", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(6, "", "music6", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(7, "", "music7", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(8, "", "music8", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(9, "", "music9", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(10, "", "music10", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(11, "", "music11", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics(12, "", "music12", "1er", "Android", "20/10/2023", "04:00:00")
    )

    private val playlists = listOf(
        MusicalPlaylists(1, "", "Test playlist", musics),
        MusicalPlaylists(2, "", "Test playlist 2", musics),
        MusicalPlaylists(3, "", "Test playlist 3", musics),
        MusicalPlaylists(3, "", "Test playlist 3", musics),
        MusicalPlaylists(3, "", "Test playlist 3", musics),
        MusicalPlaylists(3, "", "Test playlist 3", musics),
        MusicalPlaylists(3, "", "Test playlist 3", musics),
        MusicalPlaylists(3, "", "Test playlist 3", musics),
        MusicalPlaylists(3, "", "Test playlist 3", musics)
    )
    fun getPhonePlaylist(id: Int): MusicalPlaylists{
       return playlists[id-1]
    }
    fun getPhonePlaylists(): List<MusicalPlaylists>{
        return playlists
    }
}