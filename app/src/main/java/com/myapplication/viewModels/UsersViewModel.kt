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
    private var _musicalUsersCreated : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val musicalUsersCreated : LiveData<Boolean> = _musicalUsersCreated

    private var _musicalUsersUpdated : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val musicalUsersUpdated : LiveData<Boolean> = _musicalUsersUpdated

    private var _musicalUsersRemoved : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val musicalUsersRemoved : LiveData<Boolean> = _musicalUsersRemoved

    private var _spotifyUsersLiveData : MutableLiveData<Response<SpotifyUsers>?> = MutableLiveData<Response<SpotifyUsers>?>()
    val spotifyUsersRecover : LiveData<Response<SpotifyUsers>?> = _spotifyUsersLiveData

    private var _musicalUsersLiveData : MutableLiveData<MusicalUsers?> = MutableLiveData<MusicalUsers?>()
    val musicalUsersLiveData : LiveData<MusicalUsers?> = _musicalUsersLiveData

    private var _musicalUsersAuthentication : MutableLiveData<MusicalUsers?> = MutableLiveData<MusicalUsers?>()
    val musicalUsersAuthentication : LiveData<MusicalUsers?> = _musicalUsersAuthentication

    fun fetchUserByCredential(email: String, password: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                UsersMusicalRepository.getUsersByCredentials(email, password)
                    .catch {
                        Log.e("Fetch error" , it.toString())
                    }.collect{
                        _musicalUsersAuthentication.postValue(it)
                    }
            }
        }
    }

    fun createMusicalUser(pseudo: String, email: String, password: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                UsersMusicalRepository.createMusicalUser(pseudo, email, password)
                    .catch {
                        Log.e("Fetch error" , it.toString())
                    }.collect{
                        _musicalUsersCreated.postValue(it)
                    }
            }
        }
    }

    fun updateMusicalUser(users: MusicalUsers){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                UsersMusicalRepository.updateExistingUser(users)
                    .catch {
                        Log.e("Fetch error" , it.toString())
                    }.collect{
                        _musicalUsersUpdated.postValue(it)
                    }
            }
        }
    }

    fun deleteMusicalUser(users: MusicalUsers){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                UsersMusicalRepository.deleteExistingUser(users)
                    .catch {
                        Log.e("Fetch error" , it.toString())
                    }.collect{
                        _musicalUsersRemoved.postValue(it)
                    }
            }
        }
    }
    fun fetchMusicalUserByMusicalID(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                UsersMusicalRepository.getExistingUserByMusicalUserID(id)
                    .catch {
                        Log.e("Fetch error" , it.toString())
                    }
                    .collect{
                        _musicalUsersLiveData.postValue(it)
                    }
            }
        }
    }
    fun fetchSpotifyUserBySpotifyID(token: String, id : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var validToken = "Bearer $token"
                UsersSpotifyRepository.getSpotifyUsers(validToken, id)
                    .catch {
                        Log.e("Fetch error" , it.toString())
                    }
                    .collect{
                        _spotifyUsersLiveData.postValue(it)
                    }
            }
        }
    }
}