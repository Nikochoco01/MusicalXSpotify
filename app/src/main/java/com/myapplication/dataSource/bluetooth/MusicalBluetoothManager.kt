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
import java.util.UUID

class MusicalBluetoothManager {
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
	fun startBluetooth(){
		takePermission.launch(android.Manifest.permission.BLUETOOTH_CONNECT)
	}
	fun checkBluetoothStatus() : Boolean{
		return bluetoothAdapter.isEnabled
	}
	fun getPairedDevices(context: Context): MutableSet<BluetoothDevice>? {
		if (ActivityCompat.checkSelfPermission(
				context,
				Manifest.permission.BLUETOOTH_CONNECT
			) != PackageManager.PERMISSION_GRANTED
		) {
		}
		return bluetoothAdapter.bondedDevices
	}
	fun createBluetoothSocket(context: Context, device: BluetoothDevice): BluetoothSocket? {
		val uuid = UUID.randomUUID()
		val socket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
			if (ActivityCompat.checkSelfPermission(
					context,
					Manifest.permission.BLUETOOTH_CONNECT
				) != PackageManager.PERMISSION_GRANTED
			) {
				getToast(context, "Please authorize the application to use your bluetooth ", 1)
			}
			device.createRfcommSocketToServiceRecord(uuid)
		}
		return socket
	}
	fun closeBluetoothSocket(socket: BluetoothSocket): Boolean {
		try {
			socket?.close()
			return true
		} catch (e: IOException) {
			Log.e("Bluetooth error", "Error closing socket", e)
			return false
		}
	}
	fun connectToDevice(context: Context, socket: BluetoothSocket): Boolean{
		try {
			if (ActivityCompat.checkSelfPermission(
					context,
					Manifest.permission.BLUETOOTH_CONNECT
				) != PackageManager.PERMISSION_GRANTED
			) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
			}
			socket.connect()
			return isConnected(socket)
		}
		catch (e: IOException){
			Log.e("Bluetooth error", e.toString())
			return false
		}
		finally {
			closeBluetoothSocket(socket)
			return false
		}
	}
	fun isConnected(socket: BluetoothSocket): Boolean{
		return socket.isConnected
	}
	companion object {
		private lateinit var instance: MusicalBluetoothManager

		fun initBluetoothManager(context: Context){
			instance = MusicalBluetoothManager()
			instance.bluetoothManager = getSystemService(context , BluetoothManager::class.java)!!
			instance.bluetoothAdapter = instance.bluetoothManager.adapter
		}
		fun getInstance(): MusicalBluetoothManager{
			return instance
		}

	}
}