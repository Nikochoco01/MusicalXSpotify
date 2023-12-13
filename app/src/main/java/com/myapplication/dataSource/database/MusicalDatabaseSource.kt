package com.myapplication.dataSource.database

import com.myapplication.model.users.MusicalUsers

object MusicalDatabaseSource {
    suspend fun insertNewUser(users: MusicalUsers){
        MusicalDatabase.getInstance().musicalUsersDAO().insertNewUser(users)
    }
    suspend fun updateExistingUser(users: MusicalUsers){
        MusicalDatabase.getInstance().musicalUsersDAO().updateExistingUser(users)
    }
    suspend fun deleteExistingUser(users: MusicalUsers){
        MusicalDatabase.getInstance().musicalUsersDAO().deleteExistingUser(users)
    }
    suspend fun getExitingUserByCredentials(email: String , password: String){
        MusicalDatabase.getInstance().musicalUsersDAO().getExitingUserByCredentials(email , password)
    }
    suspend fun getExistingUserByMusicalUserID(usersID: Int){
        MusicalDatabase.getInstance().musicalUsersDAO().getExistingUserByMusicalUserID(usersID)
    }
    suspend fun getExistingUserBySpotifyUserID(spotifyID: Int){
        MusicalDatabase.getInstance().musicalUsersDAO().getExistingUserBySpotifyUserID(spotifyID)
    }
}