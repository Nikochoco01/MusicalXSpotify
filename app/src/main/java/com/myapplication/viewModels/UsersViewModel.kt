package com.myapplication.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.model.users.MusicalUsers
import com.myapplication.model.users.SpotifyUsers
import com.myapplication.repository.users.UsersMusicalRepository
import com.myapplication.repository.users.UsersSpotifyRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Response

class UsersViewModel : ViewModel() {
    private var _spotifyUsersLiveData : MutableLiveData<Response<SpotifyUsers>?> = MutableLiveData<Response<SpotifyUsers>?>()
    val spotifyUsersLiveData : LiveData<Response<SpotifyUsers>?> = _spotifyUsersLiveData

    private var _musicalUsersLiveData : MutableLiveData<Response<MusicalUsers>?> = MutableLiveData<Response<MusicalUsers>?>()
    val musicalUsersLiveData : LiveData<Response<MusicalUsers>?> = _musicalUsersLiveData

    fun FetchSpotifyUser(id : String){
        viewModelScope.launch {
            UsersSpotifyRepository.getUsersDetails(id)
                .catch {
                    Log.e("Fetch error" , it.toString())
                }
                .collect{
                    _spotifyUsersLiveData.postValue(it)
                }
        }
    }
    fun FetchMusicalUser(id: String){
        viewModelScope.launch {
            UsersMusicalRepository.getUsersDetails(id)
                .catch {
                    Log.e("Fetch error" , it.toString())
                }
                .collect{
                    _musicalUsersLiveData.postValue(it)
                }
        }
    }
}