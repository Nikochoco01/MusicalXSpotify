package com.myapplication.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.model.accessToken.AccessToken
import com.myapplication.repository.token.TokenRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Response

class SpotifyAPIViewModel : ViewModel() {
    private var _spotifyTokenLiveData : MutableLiveData<Response<AccessToken>?> = MutableLiveData<Response<AccessToken>?>()
    val spotifyTokenLiveData : LiveData<Response<AccessToken>?> = _spotifyTokenLiveData

    fun fetchSpotifyToken(){
        viewModelScope.launch {
                TokenRepository.getSpotifyToken()
                .catch {
                    Log.e("token error" , it.toString())
                }
                .collect{
                    _spotifyTokenLiveData.postValue(it)
                }
        }
    }
}