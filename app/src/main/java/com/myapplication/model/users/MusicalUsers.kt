package com.myapplication.model.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MusicalUsers(
	val pseudo: String,
	val mail: String,
	val password: String,
	var spotifyUsersID: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
