package com.myapplication.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import com.myapplication.viewModels.BluetoothViewModel

@Composable
fun BluetoothDialog(
	bluetoothViewModel: BluetoothViewModel,
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier
){
	LaunchedEffect(Unit){
		bluetoothViewModel.checkBluetoothStatus()
	}

	var context = LocalContext.current
	val bluetoothSocketLiveData by bluetoothViewModel.bluetoothSocketLiveData.observeAsState()
	val bluetoothSocketIsCloseLiveData by bluetoothViewModel.bluetoothSocketIsCloseLiveData.observeAsState()
	val bluetoothSocketIsConnectedLiveData by bluetoothViewModel.bluetoothSocketIsConnectedLiveData.observeAsState()
	val bluetoothStatusLiveData by bluetoothViewModel.bluetoothStatusLiveData.observeAsState(initial = false)
	val bluetoothDevicesLiveData by bluetoothViewModel.bluetoothDevicesLiveData.observeAsState()

	var bluetoothStatusRemember = remember { mutableStateOf(bluetoothStatusLiveData) }

	Dialog(
		onDismissRequest = { onDismissRequest() }
	){
		Card(
			modifier = modifier
				.height(416.dp)
				.padding(16.dp),
			shape = RoundedCornerShape(16.dp),
			colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
		) {
			Text(modifier = modifier
				.fillMaxWidth()
				.padding(16.dp),
				textAlign = TextAlign.Center,
				text = "Bluetooth management")
			Row(
				modifier
					.fillMaxWidth()
					.padding(horizontal = 16.dp),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(text = "Bluetooth")
				bluetoothStatusRemember.value?.let {bluetoothState ->
					Switch(
						checked = bluetoothState,
						onCheckedChange = {
							bluetoothStatusRemember.value = it
						}
					)
				}
			}
			LazyColumn(
				modifier
					.fillMaxWidth()
					.padding(16.dp),
				verticalArrangement = Arrangement.spacedBy(16.dp)
			){
				bluetoothDevicesLiveData?.forEach {
					device ->
					item { 
						if (ActivityCompat.checkSelfPermission(
								context,
								Manifest.permission.BLUETOOTH_CONNECT
							) != PackageManager.PERMISSION_GRANTED
						) {
						}
						Log.e("UUID", "device name: ${device.name} uuid: ${device.uuids.get(0)}")
						BluetoothItem(modifier = modifier,
							deviceName = device.name,
							onClickable = {
								bluetoothViewModel.createBluetoothSocket(context, device)
//								if(bluetoothSocketLiveData !== null)
//									bluetoothViewModel.connectToDevice(context, bluetoothSocketLiveData!!)
							}
						)
					}
				}
				if(!bluetoothStatusRemember.value)
					bluetoothViewModel.startBluetooth()
				else
					bluetoothViewModel.getPairedDevices(context)

				if(bluetoothSocketIsConnectedLiveData == true)
					Toast.makeText(context, "Connection is established", Toast.LENGTH_LONG).show()
				else
					Toast.makeText(context, "Connection is not established", Toast.LENGTH_LONG).show()

				if(bluetoothSocketIsCloseLiveData == true)
					Toast.makeText(context, "Connection is close", Toast.LENGTH_LONG).show()
				else
					Toast.makeText(context, "Connection is not close", Toast.LENGTH_LONG).show()

				if(bluetoothStatusLiveData)
					Toast.makeText(context, "Bluetooth is enable", Toast.LENGTH_LONG).show()
				else
					Toast.makeText(context, "Connection is disable", Toast.LENGTH_LONG).show()
			}
		}
	}
}