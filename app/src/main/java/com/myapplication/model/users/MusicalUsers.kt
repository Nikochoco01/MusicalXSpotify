package com.myapplication.model.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MusicalUsers(
    @PrimaryKey
    val id: Int,
    val pseudo: String,
    val mail: String,
    val password: String,
    val spotifyUsersID: Int?
)
