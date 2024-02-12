package com.myapplication.viewModels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.model.MusicalPlaylists
import com.myapplication.repository.bluetooth.MusicalControlRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MusicalControlViewModel: ViewModel() {
	private var _orderFromWatch: MutableLiveData<String> = MutableLiveData<String>()
	var orderFromWatch: LiveData<String> = _orderFromWatch
	private var _playlistToWatch : MutableLiveData<List<MusicalPlaylists>> = MutableLiveData<List<MusicalPlaylists>>()
	var playlistToWatch: LiveData<List<MusicalPlaylists>> = _playlistToWatch

	@RequiresApi(Build.VERSION_CODES.TIRAMISU)
	fun readOrder(){
		viewModelScope.launch (Dispatchers.IO){
			MusicalControlRepository.readOrders()
				.catch {
					Log.e("Bluetooth error" , "Fail to read order from watch $it")
				}
				.collect{
					_orderFromWatch.postValue(it)
					Log.e("error", "READ BLUETOOTH $it")
				}
		}
	}
	fun sendAllPlaylist(allPlaylists: List<MusicalPlaylists>){
		viewModelScope.launch (Dispatchers.IO){
			MusicalControlRepository.sendAllPlaylist(allPlaylists)
		}
	}


}