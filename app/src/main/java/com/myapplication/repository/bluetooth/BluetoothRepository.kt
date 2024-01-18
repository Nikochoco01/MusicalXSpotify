package com.myapplication.repository.bluetooth


import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import com.myapplication.dataSource.bluetooth.MusicalBluetoothService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object BluetoothRepository {
	fun startBluetooth(){
		MusicalBluetoothService.getInstance().startBluetooth()
	}
	suspend fun getPairedDevices(context: Context) : Flow<MutableSet<BluetoothDevice>?> = flow{
		emit(MusicalBluetoothService.getInstance().getPairedDevices(context))
	}
	suspend fun checkBluetoothStatus() : Flow<Boolean> = flow {
		emit(MusicalBluetoothService.getInstance().checkBluetoothStatus())
	}
	suspend fun createBluetoothSocket(context: Context, device: BluetoothDevice) : Flow<BluetoothSocket?> = flow{
		emit(MusicalBluetoothService.getInstance().createBluetoothSocket(context, device))
	}
	suspend fun closeBluetoothSocket(socket: BluetoothSocket): Flow<Boolean> = flow{
		emit(MusicalBluetoothService.getInstance().closeBluetoothSocket(socket))
	}
	suspend fun connectToDevice(context: Context, socket: BluetoothSocket): Flow<Boolean> = flow{
		emit(MusicalBluetoothService.getInstance().connectToDevice(context, socket))
	}
	suspend fun isConnected(socket: BluetoothSocket): Flow<Boolean> = flow{
		emit(MusicalBluetoothService.getInstance().isConnected(socket))
	}
}