package com.myapplication.repository.bluetooth


import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import com.myapplication.dataSource.bluetooth.MusicalBluetoothManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object BluetoothRepository {
	fun startBluetooth(){
		MusicalBluetoothManager.getInstance().startBluetooth()
	}
	suspend fun getPairedDevices(context: Context) : Flow<MutableSet<BluetoothDevice>?> = flow{
		emit(MusicalBluetoothManager.getInstance().getPairedDevices(context))
	}
	suspend fun checkBluetoothStatus() : Flow<Boolean> = flow {
		emit(MusicalBluetoothManager.getInstance().checkBluetoothStatus())
	}
	suspend fun createBluetoothSocket(context: Context, device: BluetoothDevice) : Flow<BluetoothSocket?> = flow{
		emit(MusicalBluetoothManager.getInstance().createBluetoothSocket(context, device))
	}
	suspend fun closeBluetoothSocket(socket: BluetoothSocket): Flow<Boolean> = flow{
		emit(MusicalBluetoothManager.getInstance().closeBluetoothSocket(socket))
	}
	suspend fun connectToDevice(context: Context, socket: BluetoothSocket): Flow<Boolean> = flow{
		emit(MusicalBluetoothManager.getInstance().connectToDevice(context, socket))
	}
	suspend fun isConnected(socket: BluetoothSocket): Flow<Boolean> = flow{
		emit(MusicalBluetoothManager.getInstance().isConnected(socket))
	}
}