package com.myapplication.ui.state

import com.myapplication.model.bluetooth.BluetoothDevice

data class BluetoothUiState(
	val scannedDevices: List<BluetoothDevice> = emptyList(),
	val pairedDevices: List<BluetoothDevice> = emptyList(),
)