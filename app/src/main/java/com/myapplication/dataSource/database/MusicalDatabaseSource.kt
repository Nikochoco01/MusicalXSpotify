package com.myapplication.dataSource.database

import com.myapplication.model.accessToken.AccessToken
import com.myapplication.model.users.MusicalUsers

object MusicalDatabaseSource {
    fun insertNewUser(users: MusicalUsers): Long{
        return MusicalDatabase.getInstance().musicalUsersDAO().insertNewUser(users)
    }
    fun updateExistingUser(users: MusicalUsers): Int{
        return MusicalDatabase.getInstance().musicalUsersDAO().updateExistingUser(users)
    }
    fun deleteExistingUser(users: MusicalUsers): Int{
        return MusicalDatabase.getInstance().musicalUsersDAO().deleteExistingUser(users)
    }
    fun getExitingUserByCredentials(email: String , password: String): MusicalUsers?{
        return MusicalDatabase.getInstance().musicalUsersDAO().getExitingUserByCredentials(email , password)
    }
    fun getExistingUserByMusicalUserID(usersID: Int): MusicalUsers?{
        return MusicalDatabase.getInstance().musicalUsersDAO().getExistingUserByMusicalUserID(usersID)
    }
    fun getExistingUserBySpotifyUserID(spotifyID: String): MusicalUsers?{
        return MusicalDatabase.getInstance().musicalUsersDAO().getExistingUserBySpotifyUserID(spotifyID)
    }
//    fun insertToken(token: AccessToken): Long{
//        return MusicalDatabase.getInstance().musicalSpotifyDAO().insertNewUser(token)
//    }
//    fun deleteToken(token: AccessToken): Int{
//        return MusicalDatabase.getInstance().musicalSpotifyDAO().deleteExistingToken(token)
//    }
//    fun getExitingToken(): AccessToken?{
//        return MusicalDatabase.getInstance().musicalSpotifyDAO().getExitingToken()
//    }
}