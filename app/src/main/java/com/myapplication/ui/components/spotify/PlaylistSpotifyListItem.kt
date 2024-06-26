package com.myapplication.ui.components.spotify

import android.graphics.drawable.Drawable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.R
import com.myapplication.model.SpotifyPlaylist
import com.myapplication.ui.components.MusicalPicture
import com.myapplication.ui.components.MusicalPictureFromURL

@Composable
fun PlaylistSpotifyListItem(
	playlist: SpotifyPlaylist,
	modifier: Modifier = Modifier,
	onSelectPlaylist: () -> Unit
){
	ElevatedCard(
		elevation = CardDefaults.cardElevation(
			defaultElevation = 6.dp
		),
		modifier = Modifier
			.size(width = 160.dp, height = 224.dp)
			.clip(RoundedCornerShape(12.dp))
			.clickable {
				onSelectPlaylist.invoke()
			}
	) {
		Column(
			modifier
				.fillMaxSize(),
			verticalArrangement = Arrangement.SpaceAround,
			horizontalAlignment = Alignment.CenterHorizontally) {
			if(playlist.images?.isNotEmpty() == true){
				playlist.images?.get(0)?.url?.let {
					MusicalPictureFromURL(
						modifier
							.size(128.dp)
							.clip(RoundedCornerShape(16.dp)),
						url = it, description = stringResource(id = R.string.image_playlist_from_spotify))
				}
			}
			else{
				MusicalPicture(
					modifier
						.size(128.dp)
						.clip(RoundedCornerShape(16.dp)),
					drawableResource = R.drawable.picture_1_square, description = stringResource(id = R.string.image_playlist_cant_be_load))
			}
			Text(
				text = playlist.name,
				fontSize = 24.sp,
				letterSpacing = 0.5.sp,
				lineHeight = 24.sp
			)
		}
	}
}