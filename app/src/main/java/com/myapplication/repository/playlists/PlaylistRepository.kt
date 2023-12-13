package com.myapplication.repository.playlists

import com.myapplication.dataSource.phoneFile.PhoneFilesDataSource
import com.myapplication.dataSource.spotifyApi.NetworkDataSource
import com.myapplication.model.MusicalPlaylists
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object PlaylistRepository {
    suspend fun getPlaylist(id: Int) : Flow<MusicalPlaylists> = flow {
        emit(PhoneFilesDataSource.getPhonePlaylistByID(id))
    }

    suspend fun getPlaylists(userId: String): Flow<List<MusicalPlaylists>> = flow {
        val playlistsGot: MutableList<MusicalPlaylists> = mutableListOf()
        PhoneFilesDataSource.getAllPhonePlaylists().forEach { playlists: MusicalPlaylists ->  playlistsGot.add(playlists) }
//        NetworkDataSource.apiServiceCallAPi.getAllUsersPlaylists(userId)

        emit(playlistsGot)
    }
}