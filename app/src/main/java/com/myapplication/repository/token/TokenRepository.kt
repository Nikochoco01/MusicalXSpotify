package com.myapplication.repository.token

import com.myapplication.dataSource.spotifyApi.NetworkDataSource
import com.myapplication.model.accessToken.AccessToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object TokenRepository {
    suspend fun getSpotifyToken() : Flow<Response<AccessToken>> = flow {
        emit(NetworkDataSource.apiServiceAccessToken.getAccessToken(
            "client_credentials",
            "97e4ffd132de48b994e860461e571c6a",
            "633a5550b7374b63939eeef8dbc0a091"
        ))
    }
}