package com.myapplication.dataSource.database

import com.myapplication.model.users.MusicalUsers

object MusicalDatabaseSource {
    fun insertNewUser(users: MusicalUsers){
        MusicalDatabase.getInstance().musicalUsersDAO().insertNewUser(users)
    }
    fun updateExistingUser(users: MusicalUsers){
        MusicalDatabase.getInstance().musicalUsersDAO().updateExistingUser(users)
    }
    fun deleteExistingUser(users: MusicalUsers){
        MusicalDatabase.getInstance().musicalUsersDAO().deleteExistingUser(users)
    }
    fun getExitingUserByCredentials(email: String , password: String): MusicalUsers?{
       return MusicalDatabase.getInstance().musicalUsersDAO().getExitingUserByCredentials(email , password)
    }
    fun getExistingUserByMusicalUserID(usersID: Int): MusicalUsers?{
        return MusicalDatabase.getInstance().musicalUsersDAO().getExistingUserByMusicalUserID(usersID)
    }
    fun getExistingUserBySpotifyUserID(spotifyID: Int): MusicalUsers?{
        return MusicalDatabase.getInstance().musicalUsersDAO().getExistingUserBySpotifyUserID(spotifyID)
    }
}