package com.myapplication.model.bluetooth

typealias BluetoothDeviceDomain = BluetoothDevice

data class BluetoothDevice(
	val name: String?,
	val address: String
)