package com.myapplication.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.model.MusicalPlaylists
import com.myapplication.model.SpotifyResultPlaylist
import com.myapplication.repository.playlists.PlaylistRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PlaylistViewModel : ViewModel(){
    private var _phoneFileLiveData : MutableLiveData<MusicalPlaylists> = MutableLiveData<MusicalPlaylists>()
    val phoneFileLiveData : LiveData<MusicalPlaylists> = _phoneFileLiveData

    private var _playlistsLiveData : MutableLiveData<List<MusicalPlaylists>> = MutableLiveData<List<MusicalPlaylists>>()
    val playlistsLiveData : LiveData<List<MusicalPlaylists>> = _playlistsLiveData

    private var _spotifyResultPlaylistsLiveData : MutableLiveData<SpotifyResultPlaylist> = MutableLiveData<SpotifyResultPlaylist>()
    val spotifyResultPlaylists : LiveData<SpotifyResultPlaylist> = _spotifyResultPlaylistsLiveData

    fun fetchPhoneFilePlaylist(id: Int){
        viewModelScope.launch {
            PlaylistRepository.getPlaylistByID(id)
                .catch {
                    Log.e("playlist error" , it.toString())
                }
                .collect{
                    _phoneFileLiveData.postValue(it)
                    Log.e("Test Call" , "CALL Music");
                }
        }
    }

    fun fetchAllPlaylists(userId: String){
        viewModelScope.launch {
            PlaylistRepository.getAllPhonePlaylists(userId)
                .catch {
                    Log.e("playlist error" , it.toString())
                }
                .collect{
                    _playlistsLiveData.postValue(it)
                }
        }
    }

    fun fetchAllSpotifyPlaylist(userId: String, token: String){
        viewModelScope.launch {
            var validToken = "Bearer $token"
            PlaylistRepository.getAllSpotifyPlaylists(userId, validToken)
                .catch {
                    Log.e("playlist error" , it.toString())
                }
                .collect{
                    Log.e("error" , "Result success ${it.isSuccessful}  Body ${it.body()}")
                    _spotifyResultPlaylistsLiveData.postValue(it.body())
                }
        }
    }
}