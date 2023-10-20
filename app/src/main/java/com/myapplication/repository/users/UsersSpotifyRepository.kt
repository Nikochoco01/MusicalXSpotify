package com.myapplication.repository.users

import com.myapplication.dataSource.spotifyApi.NetworkDataSource
import com.myapplication.model.users.SpotifyUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object UsersSpotifyRepository {

    suspend fun getUsersDetails(id: String) : Flow<Response<SpotifyUsers>> = flow {
      emit(NetworkDataSource.apiServiceCallAPi.getUsersById(id))
    }



//    private var listener : MutableLiveData<Response<AccessToken>> = MutableLiveData()
//    fun getToken() {
//        val credential: String = ApiAccess().grantType
//        val clientId: String = ApiAccess().clientId
//        val clientSecret: String = ApiAccess().clientSecret
//        GlobalScope.launch{
//            try {
//                val responseFlow = SpotifyClient.apiServiceAccessToken.getAccessToken(credential, clientId, clientSecret)
//
//                responseFlow.collect { responseToken ->
//                    if (responseToken.isSuccessful && responseToken.body() != null) {
//                        val content = responseToken.body()
//                        Log.d("Resp token", content?.accessToken.toString())
//                        Log.d("Resp token type", content?.tokenType.toString())
//                        Log.d("Resp token expires", content?.expiresIn.toString())
//                    } else {
//                        Log.e("Error resp message", responseToken.message())
//                        Log.e("Error resp code", responseToken.code().toString())
//                    }
//                }
//                listener.postValue(responseFlow.asLiveData().value)
//
//            } catch (e: Exception) {
//                Log.e("Error exception", "error: ${e.message}")
//            }
//        }
//    }
}