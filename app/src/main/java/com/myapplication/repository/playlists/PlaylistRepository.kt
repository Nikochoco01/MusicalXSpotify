package com.myapplication.repository.playlists

import com.myapplication.dataSource.phoneFile.PhoneFilesDataSource
import com.myapplication.model.Playlists
import com.myapplication.model.users.MusicalUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import kotlin.math.log

object PlaylistRepository {
    suspend fun getPlaylist(id: Int) : Flow<Playlists> = flow {
        emit(PhoneFilesDataSource.getPlaylist())
    }
}