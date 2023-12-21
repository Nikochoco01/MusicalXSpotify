package com.myapplication.dataSource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.myapplication.model.users.MusicalUsers

@Dao
interface MusicalUsersDAO{
    @Insert
    fun insertNewUser(user: MusicalUsers)
    @Update
    fun updateExistingUser(user: MusicalUsers)
    @Delete
    fun deleteExistingUser(users: MusicalUsers)
    @Query("SELECT * FROM MusicalUsers WHERE mail == :email AND password == :password LIMIT 1")
    fun getExitingUserByCredentials(email: String , password: String) : MusicalUsers?
    @Query("SELECT * FROM MusicalUsers WHERE id == :userID LIMIT 1")
    fun getExistingUserByMusicalUserID(userID: Int) : MusicalUsers?
    @Query("SELECT * FROM MusicalUsers WHERE spotifyUsersID == :spotifyID LIMIT 1")
    fun getExistingUserBySpotifyUserID(spotifyID: Int) : MusicalUsers?
}