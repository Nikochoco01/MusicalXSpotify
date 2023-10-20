package com.myapplication.repository.playlists

import com.myapplication.model.Playlists
import com.myapplication.model.users.MusicalUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object PlaylistRepository {
    suspend fun getPlaylist(id: String) : Flow<Response<Playlists>> = flow {
        // emit()
    }
}