package com.myapplication.repository.playlists

import android.content.Context
import android.net.Uri
import com.myapplication.dataSource.phoneFile.PhoneFilesDataSource
import com.myapplication.model.MusicalPlaylists
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object PlaylistRepository {

    suspend fun createPlaylist(context: Context, fileUri: Uri): Flow<Boolean> = flow{
        emit(PhoneFilesDataSource.createPlaylist(context, fileUri))
    }
    suspend fun getPlaylistByID(id: Int) : Flow<MusicalPlaylists> = flow {
        emit(PhoneFilesDataSource.getPlaylistByID(id))
    }

    suspend fun getAllPlaylists(userId: String): Flow<List<MusicalPlaylists>> = flow {
        val playlistsGot: MutableList<MusicalPlaylists> = mutableListOf()
        PhoneFilesDataSource.getAllPlaylists().forEach { playlists: MusicalPlaylists ->  playlistsGot.add(playlists) }
//        NetworkDataSource.apiServiceCallAPi.getAllUsersPlaylists(userId)

        emit(playlistsGot)
    }
}