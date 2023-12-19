package com.myapplication.repository.users

import android.util.Log
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution
import com.myapplication.dataSource.database.MusicalDatabaseSource
import com.myapplication.model.users.MusicalUsers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response

object UsersMusicalRepository {

    suspend fun getUsersByCredentials(email: String , password : String) : Flow<MusicalUsers?> = flow {
        emit(MusicalDatabaseSource.getExitingUserByCredentials(email, password))
    }

    fun createMusicalUser(pseudo: String, email: String, password: String){
        val user = MusicalUsers(pseudo, email, password, null)
        MusicalDatabaseSource.insertNewUser(user)
    }

    suspend fun getUsersDetails(id: String) : Flow<Response<MusicalUsers>> = flow {
       // emit()
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