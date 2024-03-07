package com.myapplication.repository.playlists

import com.myapplication.dataSource.phoneFile.PhoneFilesDataSource
import com.myapplication.dataSource.spotifyApi.NetworkDataSource
import com.myapplication.model.MusicalPlaylists
import com.myapplication.model.SpotifyResultPlaylist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object PlaylistRepository {
    suspend fun getPlaylistByID(id: Int) : Flow<MusicalPlaylists> = flow {
        emit(PhoneFilesDataSource.getPhonePlaylistByID(id))
    }

    suspend fun getAllPhonePlaylists(userId: String): Flow<List<MusicalPlaylists>> = flow {
        val playlistsGot: MutableList<MusicalPlaylists> = mutableListOf()
        PhoneFilesDataSource.getAllPhonePlaylists().forEach { playlists: MusicalPlaylists ->  playlistsGot.add(playlists) }
        emit(playlistsGot)
    }

    suspend fun getAllSpotifyPlaylists(userId: String, authorization: String): Flow<Response<SpotifyResultPlaylist>> = flow{
        emit(NetworkDataSource.apiServiceCallAPi.getAllUsersPlaylists(userId, authorization))
    }
}