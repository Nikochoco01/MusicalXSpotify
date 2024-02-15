package com.myapplication.dataSource.bluetooth


import com.myapplication.model.bluetooth.BluetoothDevice
import kotlinx.coroutines.flow.StateFlow

interface IBluetoothController {
	val scannedDevices: StateFlow<List<BluetoothDevice>>
	val pairedDevices: StateFlow<List<BluetoothDevice>>

	fun startDiscovery()
	fun stopDiscovery()

	fun release()
}