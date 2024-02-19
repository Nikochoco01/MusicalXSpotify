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

// var listener: MutableLiveData<Response<SpotifyUsers>> = MutableLiveData()

   // suspend fun getUsersDetails() = SpotifyClient.apiServiceCallAPi.getUsersById()

//    suspend fun getUsersDetails(): Flow<Response<SpotifyUsers>> = flow {
//        SpotifyClient.apiServiceCallAPi.getUsersById().collect(){
//            emit(it)
//        }
//    }

//    fun getUsersDetails(){
//        val token: String = AccessToken().accessToken
//        val type: String = AccessToken().tokenType
//        val duration: String = AccessToken().expiresIn
//        GlobalScope.launch {
//            try {
//                val responseFlow = SpotifyClient.apiServiceCallAPi.getUsersById()
//                responseFlow.collect{ gettedUsers ->
//                    if (gettedUsers.isSuccessful && gettedUsers.body() != null){
//                        val content = gettedUsers.body()
//                        Log.d("Resp" , content?.id.toString())
//                        Log.d("Resp" , content?.images.toString())
//                        Log.d("Resp" , content?.name.toString())
//                    }
//                    else{
//                        Log.e("Error", "Error code: ${gettedUsers.code()}")
//                        Log.e("Error", "Error message: ${gettedUsers.message()}")
//                    }
//                }
//
//            }
//            catch (e:Exception){
//                Log.e("Error", "Error: ${e.message}")
//            }
//        }
//    }
}