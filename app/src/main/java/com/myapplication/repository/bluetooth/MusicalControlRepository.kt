package com.myapplication.repository.bluetooth

import android.os.Build
import androidx.annotation.RequiresApi
import com.myapplication.dataSource.bluetooth.MusicalBluetoothService
import com.myapplication.model.MusicalPlaylists
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MusicalControlRepository {
	@RequiresApi(Build.VERSION_CODES.TIRAMISU)
	suspend fun readOrders(): Flow<String> = flow {
		emit(MusicalBluetoothService.getInstance().readOrders())
	}
	fun sendAllPlaylist(allPlaylist: List<MusicalPlaylists>){
		MusicalBluetoothService.getInstance().sendAllPlaylist(allPlaylist)
	}
	fun sendChoosePlaylist(id: Int){
		MusicalBluetoothService.getInstance().sendPlaylistById(id)
	}
	fun playMusic(){
		MusicalBluetoothService.getInstance().playMusic()
	}
	fun pauseMusic(){
		MusicalBluetoothService.getInstance().pauseMusic()
	}
	fun nextMusic(id: Int){
		MusicalBluetoothService.getInstance().nextMusic(id)
	}
	fun previousMusic(id: Int){
		MusicalBluetoothService.getInstance().previousMusic(id)
	}
}