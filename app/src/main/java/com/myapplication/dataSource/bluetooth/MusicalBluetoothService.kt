package com.myapplication.dataSource.bluetooth

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import java.io.IOException
import java.util.UUID

class MusicalBluetoothService {
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
//			getToast(context, "If you want to use a smart watch, enable Bluetooth in settings", 1)
		}
	}
	private fun handleActivityResult(result: ActivityResult, context: Context) {
		if (result.resultCode == Activity.RESULT_OK) {
//			getToast(context, "Your Bluetooth is already activated", 1)
		} else {
//			getToast(context, "Your Bluetooth is not activated", 1)
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
		val serverUUID = UUID.fromString("5238c3c0-ddf6-4a48-bab7-7ec1f037b0a2")
		var serverSocket: BluetoothServerSocket? = null
		var socket: BluetoothSocket? = null
		if (ActivityCompat.checkSelfPermission(
				context,
				Manifest.permission.BLUETOOTH_CONNECT
			) != PackageManager.PERMISSION_GRANTED
		) {
		}

		try {
			serverSocket = bluetoothAdapter?.listenUsingRfcommWithServiceRecord("MusicalXSpotify", serverUUID)!!
			if(serverSocket != null)
				socket = serverSocket.accept()
			return socket
		}
		catch (e:IOException){
			Log.e("Bluetooth error", "Create server socket error: $e")
			serverSocket?.close()
			return null
		}
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
			Log.e("Bluetooth error", "Connect to device error: $e")
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
		private lateinit var instance: MusicalBluetoothService

		fun initBluetoothManager(context: Context){
			instance = MusicalBluetoothService()
			instance.bluetoothManager = getSystemService(context , BluetoothManager::class.java)!!
			instance.bluetoothAdapter = instance.bluetoothManager.adapter
		}
		fun getInstance(): MusicalBluetoothService{
			return instance
		}

	}
}