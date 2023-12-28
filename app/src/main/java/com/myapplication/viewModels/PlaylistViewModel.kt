package com.myapplication.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.model.MusicalPlaylists
import com.myapplication.repository.playlists.PlaylistRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PlaylistViewModel : ViewModel(){
    private var _playlistByIdLiveData : MutableLiveData<MusicalPlaylists> = MutableLiveData<MusicalPlaylists>()
    val playlistByIdLiveData : LiveData<MusicalPlaylists> = _playlistByIdLiveData

    private var _allPlaylistsLiveData : MutableLiveData<List<MusicalPlaylists>> = MutableLiveData<List<MusicalPlaylists>>()
    val allPlaylistsLiveData : LiveData<List<MusicalPlaylists>> = _allPlaylistsLiveData

    fun fetchPlaylistByID(id: Int){
        viewModelScope.launch {
            PlaylistRepository.getPlaylistByID(id)
                .catch {
                    Log.e("playlist error" , it.toString())
                }
                .collect{
                    _playlistByIdLiveData.postValue(it)
                    Log.e("Test Call" , "CALL Music");
                }
        }
    }

    fun fetchAllPlaylists(userId: String){
        Log.e("Test Call" , "CALL Playlist");
        viewModelScope.launch {
            PlaylistRepository.getAllPlaylists(userId)
                .catch {
                    Log.e("playlist error" , it.toString())
                }
                .collect{
                    _allPlaylistsLiveData.postValue(it)
                }
        }
    }
}