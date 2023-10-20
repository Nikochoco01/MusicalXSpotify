package com.myapplication.repository.users

import com.myapplication.model.users.MusicalUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object UsersMusicalRepository {
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