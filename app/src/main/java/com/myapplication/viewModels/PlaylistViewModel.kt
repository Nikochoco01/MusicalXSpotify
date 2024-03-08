package com.myapplication.viewModels

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.model.MusicalPlaylists
import com.myapplication.model.SpotifyResultPlaylist
import com.myapplication.model.SpotifyResultTracks
import com.myapplication.repository.playlists.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaylistViewModel : ViewModel(){
    private var _createPlaylistLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val createPlaylistLiveData: LiveData<Boolean> = _createPlaylistLiveData

    private var _playlistPhoneById : MutableLiveData<MusicalPlaylists> = MutableLiveData<MusicalPlaylists>()
    val playlistPhoneById : LiveData<MusicalPlaylists> = _playlistPhoneById

    private var _spotifyResultPlaylistsLiveData : MutableLiveData<SpotifyResultPlaylist> = MutableLiveData<SpotifyResultPlaylist>()
    val spotifyResultPlaylists : LiveData<SpotifyResultPlaylist> = _spotifyResultPlaylistsLiveData

    private var _spotifyResultTracksPlaylist : MutableLiveData<SpotifyResultTracks> = MutableLiveData<SpotifyResultTracks>()
    val spotifyResultTracksPlaylist : LiveData<SpotifyResultTracks> = _spotifyResultTracksPlaylist

    private var _allPhonePlaylists : MutableLiveData<List<MusicalPlaylists>> = MutableLiveData<List<MusicalPlaylists>>()
    val allPhonePlaylists : LiveData<List<MusicalPlaylists>> = _allPhonePlaylists

    fun createPlaylist(context: Context, fileUri: Uri){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                PlaylistRepository.createPlaylist(context, fileUri)
                    .catch {
                        Log.e("playlist error", it.toString())
                    }
                    .collect{
                        _createPlaylistLiveData.postValue(it)
                    }
            }
        }
    }

    fun fetchPlaylistByID(id: Int){
        viewModelScope.launch {
            PlaylistRepository.getPlaylistByID(id)
                .catch {
                    Log.e("playlist error" , it.toString())
                }
                .collect{
                    _playlistPhoneById.postValue(it)
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
                    _allPhonePlaylists.postValue(it)
                }
        }
    }

    fun fetchAllSpotifyPlaylist(userId: String, token: String){
        viewModelScope.launch {
            var validToken = "Bearer $token"
            PlaylistRepository.getAllSpotifyPlaylists(userId, validToken)
                .catch {
                    Log.e("fetch playlists error" , it.toString())
                }
                .collect{
                    Log.e("error" , "Result success ${it.isSuccessful}  Body ${it.body()}")
                    _spotifyResultPlaylistsLiveData.postValue(it.body())
                }
        }
    }

    fun fetchAllTracksFromPlaylist(playlistId: String, token: String){
        viewModelScope.launch {
            var validToken = "Bearer $token"
            PlaylistRepository.getAllTracksFromPlaylist(playlistId, validToken)
                .catch {
                    Log.e("fetch tracks error" , it.toString())
                }
                .collect{
                    Log.e("error" , "Result success ${it.isSuccessful}  Body ${it.body()}")
                    _spotifyResultTracksPlaylist.postValue(it.body())
                }
        }
    }
}