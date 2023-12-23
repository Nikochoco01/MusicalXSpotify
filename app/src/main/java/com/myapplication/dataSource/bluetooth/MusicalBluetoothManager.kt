package com.myapplication.dataSource.bluetooth

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.ParcelUuid
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class MusicalBluetoothManager {
//	val uuid : UUID = UUID.fromString("4170ad80-2f8a-4019-a23a-506dfe1f4e2f")
	var isActivated: Boolean = false
	lateinit var bluetoothManager: BluetoothManager
	lateinit var bluetoothAdapter: BluetoothAdapter
	private lateinit var takePermission: ActivityResultLauncher<String>
	private lateinit var takeResultLauncher: ActivityResultLauncher<Intent>

	fun initPermission(activity: ComponentActivity){
		takePermission = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isPermissionGranted ->
			handlePermissionResult(isPermissionGranted, activity)
		}

		takeResultLauncher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			handleActivityResult(result, activity)
		}
	}

	/**
	 * @param length is a Int value 0 for LENGTH_SHORT : 1 for LENGTH_LONG
	 */
	private fun getToast(context: Context, message: String, length: Int){
		Toast.makeText(context, message, length).show()
	}

	private fun handlePermissionResult(isPermissionGranted: Boolean, context: Context){
		if (isPermissionGranted) {
			val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
			takeResultLauncher.launch(intent)
		} else {
			getToast(context, "If you want to use a smart watch, enable Bluetooth in settings", 1)
		}
	}

	private fun handleActivityResult(result: ActivityResult, context: Context) {
		if (result.resultCode == Activity.RESULT_OK) {
			getToast(context, "Your Bluetooth is already activated", 1)
		} else {
			getToast(context, "Your Bluetooth is not activated", 1)
		}
	}

//	fun startBluetooth(){
//		takePermission.launch(android.Manifest.permission.BLUETOOTH_CONNECT)
//	}
//
//	fun stopBluetooth(context: Context){
////		if (ActivityCompat.checkSelfPermission(context,
////				Manifest.permission.BLUETOOTH_CONNECT
////			) != PackageManager.PERMISSION_GRANTED
////		) {
////		}
////		bluetoothAdapter.disable()
//	}

	fun getPairedDevices(context: Context): MutableSet<BluetoothDevice>? {
		if (ActivityCompat.checkSelfPermission(
				context,
				Manifest.permission.BLUETOOTH_CONNECT
			) != PackageManager.PERMISSION_GRANTED
		) {
		}
		return bluetoothAdapter.bondedDevices
	}

	fun connectToDevice(context: Context, device: BluetoothDevice, uuid: ParcelUuid){
		val socket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
			if (ActivityCompat.checkSelfPermission(
					context,
					Manifest.permission.BLUETOOTH_CONNECT
				) != PackageManager.PERMISSION_GRANTED
			) {
			}
			device.createRfcommSocketToServiceRecord(uuid.uuid)
		}

//		bluetoothAdapter?.cancelDiscovery()

		GlobalScope.launch (Dispatchers.IO){
			try {
				socket?.connect()
			}
			catch (e:IOException){
				Log.e("Bluetooth_connection", e.toString())
			}
		}
	}

	companion object {
		private lateinit var instance: MusicalBluetoothManager

		fun initBluetoothManager(context: Context){
			instance = MusicalBluetoothManager()
			instance.isActivated = false
			instance.bluetoothManager = getSystemService(context , BluetoothManager::class.java)!!
			instance.bluetoothAdapter = instance.bluetoothManager.adapter
		}
		fun getInstance(): MusicalBluetoothManager{
			return instance
		}

	}
}