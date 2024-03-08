package com.myapplication.dataSource.spotifyApi

import com.myapplication.model.MusicalPlaylists
import com.myapplication.model.Musics
import com.myapplication.model.SpotifyMusics
import com.myapplication.model.SpotifyResultPlaylist
import com.myapplication.model.SpotifyResultTracks
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

    @GET("users/{user_id}")
    suspend fun getUsersById(
        @Path("user_id") id: String,
        @Header("Authorization") authorization: String
    ): Response<SpotifyUsers>

    @GET("users/{user_id}/playlists")
    suspend fun getAllUsersPlaylists(
        @Path("user_id") id: String,
        @Header("Authorization") authorization: String
    ): Response<SpotifyResultPlaylist>

    @GET("playlists/{playlist_id}/tracks")
    suspend fun getAllTracksFromPlaylist(
        @Path("playlist_id") id: String,
        @Header("Authorization") authorization: String
    ): Response<SpotifyResultTracks>

    @GET("playlists/{playlist_id}")
    suspend fun getPlaylistById(
        @Path("playlist_id") id: String,
        @Header("Authorization") authorization: String
    ): Response<MusicalPlaylists>

    @GET("tracks/{music_id}")
    suspend fun getMusicById(
        @Path("music_id") id: String,
        @Header("Authorization") authorization: String
    ): Response<Musics>
}