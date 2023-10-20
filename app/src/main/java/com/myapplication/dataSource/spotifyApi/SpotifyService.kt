package com.myapplication.dataSource.spotifyApi


import com.myapplication.model.accessToken.AccessToken
import com.myapplication.model.users.SpotifyUsers
import retrofit2.Response
import retrofit2.http.*


interface SpotifyService {
    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Response<AccessToken>

    @Headers("Authorization: Bear ")
    @GET("/users/{id}")
    suspend fun getUsersById(@Field("id") id: String): Response<SpotifyUsers>

}