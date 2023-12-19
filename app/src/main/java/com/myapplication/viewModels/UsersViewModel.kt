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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class UsersViewModel : ViewModel() {
    private var _spotifyUsersLiveData : MutableLiveData<Response<SpotifyUsers>?> = MutableLiveData<Response<SpotifyUsers>?>()
    val spotifyUsersLiveData : LiveData<Response<SpotifyUsers>?> = _spotifyUsersLiveData

    private var _musicalUsersLiveData : MutableLiveData<Response<MusicalUsers>?> = MutableLiveData<Response<MusicalUsers>?>()
    val musicalUsersLiveData : LiveData<Response<MusicalUsers>?> = _musicalUsersLiveData

    private var _musicalUsersLiveDataNotResponse : MutableLiveData<MusicalUsers?> = MutableLiveData<MusicalUsers?>()
    val musicalUsersLiveDataNotResponse : LiveData<MusicalUsers?> = _musicalUsersLiveDataNotResponse

    fun fetchUserByCredential(email: String, password: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                UsersMusicalRepository.getUsersByCredentials(email, password)
                    .catch {
                        Log.e("Fetch error" , it.toString())
                    }.collect{
                        _musicalUsersLiveDataNotResponse.postValue(it)
                    }
            }
        }
    }

    fun createMusicalUser(pseudo: String, email: String, password: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                UsersMusicalRepository.createMusicalUser(pseudo, email, password)
            }
        }
    }

    fun fetchSpotifyUser(id : String){
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
    fun fetchMusicalUser(id: String){
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