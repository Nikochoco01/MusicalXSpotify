package com.myapplication.repository.users

import com.myapplication.dataSource.database.MusicalDatabaseSource
import com.myapplication.model.users.MusicalUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object UsersMusicalRepository {
    suspend fun getUsersByCredentials(email: String , password : String) : Flow<MusicalUsers?> = flow {
        emit(MusicalDatabaseSource.getExitingUserByCredentials(email, password))
    }

    fun createMusicalUser(pseudo: String, email: String, password: String): Flow<Boolean> = flow{
        val user = MusicalUsers(pseudo, email, password, null)
        val result = MusicalDatabaseSource.insertNewUser(user)
        // -1 operation failed ... !-1 operation success
        if(result.toInt() == -1)
            emit(false)
        else
            emit(true)
    }

    fun updateExistingUser(user: MusicalUsers): Flow<Boolean> = flow{
        val result = MusicalDatabaseSource.updateExistingUser(user)
        if(result > 0)
            emit(true)
        else
            emit(false)
    }

    suspend fun getExistingUserByMusicalUserID(id: Int) : Flow<MusicalUsers?> = flow {
        emit(MusicalDatabaseSource.getExistingUserByMusicalUserID(id))
    }
    suspend fun getExistingUserBySpotifyUserID(id: String) : Flow<MusicalUsers?> = flow {
        emit(MusicalDatabaseSource.getExistingUserBySpotifyUserID(id))
    }

    fun deleteExistingUser(user: MusicalUsers): Flow<Boolean> = flow{
        val result = MusicalDatabaseSource.deleteExistingUser(user)
        if(result == 0)
            emit(false)
        else
            emit(true)
    }

}