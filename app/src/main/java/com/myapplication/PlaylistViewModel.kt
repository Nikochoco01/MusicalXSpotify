package com.myapplication

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
    private var _phoneFileLiveData : MutableLiveData<MusicalPlaylists> = MutableLiveData<MusicalPlaylists>()
    val phoneFileLiveData : LiveData<MusicalPlaylists> = _phoneFileLiveData

    fun fetchPhoneFilePlaylist(id: Int){
        Log.e("Test Call" , "CALL");
        viewModelScope.launch {
            PlaylistRepository.getPlaylist(id)
                .catch {
                    Log.e("playlist error" , it.toString())
                }
                .collect{
                    _phoneFileLiveData.postValue(it)
                }
        }
    }
}