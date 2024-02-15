package com.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.model.bluetooth.BluetoothDevice

@Composable
fun BluetoothDeviceList(
	pairedDevices: List<BluetoothDevice>,
	scannedDevices: List<BluetoothDevice>,
	onClick: (BluetoothDevice) -> Unit,
	modifier: Modifier = Modifier
) {
	LazyColumn(
		modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		item {
			Text(
				text = "Paired Devices",
				fontWeight = FontWeight.Bold,
				fontSize = 24.sp,
				modifier = Modifier.padding(16.dp)
			)
		}
		items(pairedDevices) { device ->
			BluetoothListItem(modifier = modifier, deviceName = device.name ?: "(No name)", onClickable = {
				onClick(device)
			})
		}

		item {
			Text(
				text = "Scanned Devices",
				fontWeight = FontWeight.Bold,
				fontSize = 24.sp,
				modifier = Modifier.padding(16.dp)
			)
		}
		items(scannedDevices) { device ->
			BluetoothListItem(modifier = modifier, deviceName = device.name ?: "(No name)", onClickable = {
				onClick(device)
			})
		}
	}
}