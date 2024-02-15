package com.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.myapplication.ui.state.BluetoothUiState

@Composable
fun BluetoothDialog(
	state: BluetoothUiState,
	onDismissRequest: () -> Unit,
	onStartScan: () -> Unit,
	onStopScan: () -> Unit,
	modifier: Modifier = Modifier
){
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

			BluetoothDeviceList(
				pairedDevices = state.pairedDevices,
				scannedDevices = state.scannedDevices,
				onClick = {},
				modifier = Modifier
					.fillMaxWidth()
					.weight(1f)
			)
			Row(
				modifier = Modifier.fillMaxWidth()
					.padding(16.dp),
				horizontalArrangement = Arrangement.SpaceAround,
				content = {
					Button(onClick = onStartScan) {
						Text(text = "Start scan")
					}
					Button(onClick = onStopScan) {
						Text(text = "Stop scan")
					}
				}
			)
		}
	}
}