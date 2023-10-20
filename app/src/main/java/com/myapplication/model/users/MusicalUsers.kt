package com.myapplication.model.users

data class MusicalUsers(
    val id: Int,
    val mail: String,
    val password: String,
    val spotifyUsers: SpotifyUsers
)
