package com.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.myapplication.R

@Composable
fun MusicalProgress(
	modifier: Modifier,
	circleColor: Color,
	onDismiss: () -> Unit
){
	Dialog(onDismissRequest = { onDismiss.invoke() }) {
		Column (
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		){
			Text(text = stringResource(id = R.string.dialog_connecting))
			Text(text = stringResource(id = R.string.dialog_connecting_watch))
			CircularProgressIndicator(
				modifier = modifier.width(64.dp),
				color = circleColor
			)
		}
	}
}