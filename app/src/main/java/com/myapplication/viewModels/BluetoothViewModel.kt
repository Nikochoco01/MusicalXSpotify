package com.myapplication.viewModels

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myapplication.repository.bluetooth.BluetoothRepository
import kotlinx.coroutines.flow.catch

class BluetoothViewModel: ViewModel(){

	private var _bluetoothSocketLiveData : MutableLiveData<BluetoothSocket?> = MutableLiveData<BluetoothSocket?>()
	val bluetoothSocketLiveData : LiveData<BluetoothSocket?> = _bluetoothSocketLiveData

	private var _bluetoothSocketIsCloseLiveData : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
	val bluetoothSocketIsCloseLiveData : LiveData<Boolean> = _bluetoothSocketIsCloseLiveData

	private var _bluetoothSocketIsConnectedLiveData : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
	val bluetoothSocketIsConnectedLiveData : LiveData<Boolean> = _bluetoothSocketIsConnectedLiveData

	private var _bluetoothStatusLiveData : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
	val bluetoothStatusLiveData : LiveData<Boolean> = _bluetoothStatusLiveData

	private var _bluetoothDevicesLiveData : MutableLiveData<MutableSet<BluetoothDevice>?> = MutableLiveData<MutableSet<BluetoothDevice>?>()
	val bluetoothDevicesLiveData : LiveData<MutableSet<BluetoothDevice>?> = _bluetoothDevicesLiveData
	fun checkBluetoothStatus() {
		viewModelScope.launch(Dispatchers.IO) {
			BluetoothRepository.checkBluetoothStatus()
				.catch {
					Log.e("bluetooth error" , it.toString())
				}
				.collect{
					_bluetoothStatusLiveData.postValue(it)
				}
		}
	}
	fun startBluetooth() {
		viewModelScope.launch(Dispatchers.IO) {
			BluetoothRepository.startBluetooth()
		}
	}
	fun getPairedDevices(context: Context){
		viewModelScope.launch(Dispatchers.IO) {
			BluetoothRepository.getPairedDevices(context)
				.catch {
					Log.e("bluetooth error" , it.toString())
				}
				.collect{
					_bluetoothDevicesLiveData.postValue(it)
				}
		}
	}
	fun createBluetoothSocket(context: Context, device: BluetoothDevice){
		viewModelScope.launch(Dispatchers.IO) {
			BluetoothRepository.createBluetoothSocket(context, device)
				.catch {
					Log.e("bluetooth error" , it.toString())
				}
				.collect{
					_bluetoothSocketLiveData.postValue(it)
				}
		}
	}
	fun closeBluetoothSocket(socket: BluetoothSocket){
		viewModelScope.launch(Dispatchers.IO) {
			BluetoothRepository.closeBluetoothSocket(socket)
				.catch {
					Log.e("bluetooth error" , it.toString())
				}
				.collect{
					_bluetoothSocketIsCloseLiveData.postValue(it)
				}
		}
	}
	fun connectToDevice(context: Context, socket: BluetoothSocket) {
		viewModelScope.launch(Dispatchers.IO) {
			BluetoothRepository.connectToDevice(context, socket)
		}
	}
	fun isConnected(socket: BluetoothSocket){
		viewModelScope.launch(Dispatchers.IO) {
			BluetoothRepository.isConnected(socket)
				.catch {
					Log.e("bluetooth error" , it.toString())
				}
				.collect{
					_bluetoothSocketIsConnectedLiveData.postValue(it)
				}
		}
	}
}