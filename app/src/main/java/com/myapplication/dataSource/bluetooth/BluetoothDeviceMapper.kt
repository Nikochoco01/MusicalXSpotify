package com.myapplication.dataSource.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.myapplication.model.bluetooth.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
	return BluetoothDeviceDomain(
		name = name,
		address = address
	)
}