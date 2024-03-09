package com.myapplication.ui.components.spotify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.myapplication.R
import com.myapplication.ui.utils.MusicalIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotifyDialog(
	modifier: Modifier,
	spotifyID: String,
	onSaveRequest: (String) -> Unit,
	onDismissRequest: () -> Unit
){
	var spotifyUserID by rememberSaveable { mutableStateOf(spotifyID) }
	Dialog(onDismissRequest = { onDismissRequest.invoke()}) {
		Card (
			modifier = modifier
				.height(240.dp)
				.padding(16.dp),
			shape = RoundedCornerShape(16.dp),
			colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
		){
			Column (
				modifier = modifier.fillMaxSize(),
				verticalArrangement = Arrangement.SpaceEvenly
			){
				Text(modifier = modifier.fillMaxWidth(),
					text = "Register your Spotify user id",
					textAlign = TextAlign.Center
				)
				OutlinedTextField(
					value = spotifyUserID,
					onValueChange = { spotifyUserID = it },
					modifier
						.fillMaxWidth()
						.padding(16.dp),
					label = { Text(text = stringResource(id = R.string.label_input_spotify_id)) },
					placeholder = { Text(text = stringResource(id = R.string.placeholder_input_spotify_id)) }
				)
				Row (
					modifier = modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceEvenly,
					verticalAlignment = Alignment.CenterVertically
				){
					OutlinedButton(
						onClick = { onDismissRequest.invoke() },
						content = {
							Icon(imageVector = MusicalIcons.iconClose,
								contentDescription = stringResource(id = R.string.icon_close)
							)
							Text(text = stringResource(id = R.string.action_close_dialog))
						}
					)
					Button(
						onClick = { onSaveRequest.invoke(spotifyUserID) },
						content = {
							Icon(imageVector = MusicalIcons.iconCheck,
								contentDescription = stringResource(id = R.string.icon_check)
							)
							Text(text = stringResource(id = R.string.action_save_dialog))
						}
					)
				}
			}
		}
	}
}