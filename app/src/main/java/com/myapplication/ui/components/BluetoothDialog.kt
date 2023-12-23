package com.myapplication.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
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
import androidx.compose.runtime.getValue
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
import com.myapplication.dataSource.bluetooth.MusicalBluetoothManager

@Composable
fun BluetoothDialog(
	musicalBluetoothManager: MusicalBluetoothManager,
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier
){
	var context = LocalContext.current
	var bluetoothIsActivated by remember { mutableStateOf(musicalBluetoothManager.bluetoothAdapter.isEnabled) }
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
				Switch(
					checked = bluetoothIsActivated,
					onCheckedChange = {
						bluetoothIsActivated = it
					}
				)
			}

//			if(bluetoothIsActivated)
//				musicalBluetoothManager.startBluetooth()
//			else {
//				musicalBluetoothManager.stopBluetooth(context)
//				Log.e("BT" , "STOP BT")
//			}

			LazyColumn(
				modifier
					.fillMaxWidth()
					.padding(16.dp),
				verticalArrangement = Arrangement.spacedBy(16.dp)
			){

				val pairedDevices = musicalBluetoothManager.getPairedDevices(context)
				pairedDevices?.forEach {
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
							onClickable = {musicalBluetoothManager.connectToDevice(context, device, device.uuids.get(0))})
					}
				}
			}
		}
	}
}