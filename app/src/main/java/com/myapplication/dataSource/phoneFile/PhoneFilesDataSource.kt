package com.myapplication.dataSource.phoneFile

import com.myapplication.model.Musics
import com.myapplication.model.MusicalPlaylists

object PhoneFilesDataSource {
    private val musics = arrayListOf(
        Musics("1", "", "music1", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("2", "", "music2", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("3", "", "music3", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("4", "", "music4", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("5", "", "music5", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("6", "", "music6", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("7", "", "music7", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("8", "", "music8", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("9", "", "music9", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("10", "", "music10", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("11", "", "music11", "1er", "Android", "20/10/2023", "04:00:00"),
        Musics("12", "", "music12", "1er", "Android", "20/10/2023", "04:00:00")
    )

    private val playlists = listOf(
        MusicalPlaylists("1", "null", "Test playlist", musics),
        MusicalPlaylists("2", "null", "Test playlist 2", musics),
        MusicalPlaylists("3", "null", "Test playlist 3", musics),
        MusicalPlaylists("4", "null", "Test playlist 4", musics),
        MusicalPlaylists("5", "null", "Test playlist 5", musics),
        MusicalPlaylists("6", "null", "Test playlist 6", musics),
        MusicalPlaylists("7", "null", "Test playlist 7", musics),
        MusicalPlaylists("8", "null", "Test playlist 8", musics),
        MusicalPlaylists("9", "null", "Test playlist 9", musics)
    )
    fun getPhonePlaylistByID(id: Int): MusicalPlaylists{
       return playlists[id - 1]
    }
    fun getAllPhonePlaylists(): List<MusicalPlaylists>{
        return playlists
    }
}