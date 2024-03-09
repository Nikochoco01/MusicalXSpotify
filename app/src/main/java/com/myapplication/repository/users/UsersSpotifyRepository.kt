package com.myapplication.repository.users

import android.util.Log
import com.myapplication.dataSource.spotifyApi.NetworkDataSource
import com.myapplication.model.users.SpotifyUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object UsersSpotifyRepository {

    suspend fun getSpotifyUsers(authorization: String, id: String) : Flow<Response<SpotifyUsers>> = flow {
        emit(NetworkDataSource.apiServiceCallAPi.getUsersById(id, authorization))
    }
}