package com.myapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BluetoothListItem(
	modifier: Modifier,
	deviceName: String,
	onClickable: () -> Unit
){
	ListItem(
		modifier = modifier
			.clip(RoundedCornerShape(16.dp))
			.clickable { onClickable.invoke() },
		headlineText = { Text(text = deviceName, color = MaterialTheme.colorScheme.inversePrimary) },
		colors = ListItemDefaults.colors(
			containerColor = MaterialTheme.colorScheme.onPrimaryContainer
		)
	)
}