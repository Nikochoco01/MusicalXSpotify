package com.myapplication.ui.components.spotify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.R
import com.myapplication.model.SpotifyMusics
import com.myapplication.ui.components.MusicalPicture
import com.myapplication.ui.components.MusicalPictureFromURL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicSpotifyListItem(
	music: SpotifyMusics,
	modifier: Modifier = Modifier
){
	ListItem(
		modifier = modifier.clip(RoundedCornerShape(16.dp)),
		colors = ListItemDefaults.colors(
			containerColor = MaterialTheme.colorScheme.surfaceVariant
		),
		leadingContent = {
			if(music.album.albumCover?.isNotEmpty() == true){
				MusicalPictureFromURL(modifier.size(56.dp).clip(RoundedCornerShape(24)),
					url = music.album.albumCover[0].url, description = stringResource(id = R.string.image_music_from_spotify))
			}
			else{
				MusicalPicture(
					modifier
						.size(128.dp)
						.clip(RoundedCornerShape(16.dp)),
					drawableResource = R.drawable.picture_1_square, description = stringResource(id = R.string.image_music_cant_be_load)
				)
			}
		},
		headlineText = {
			Text(text = music.title,
				style = TextStyle(
					fontSize = 16.sp,
					lineHeight = 24.sp,
					letterSpacing = 0.5.sp,
					fontWeight = FontWeight(500),
				)
			)
		},
		supportingText = {
			Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
				Text(
					text = music.artist[0].name,
					style = TextStyle(
						fontSize = 14.sp,
						lineHeight = 20.sp,
						fontWeight = FontWeight(400),
						color = MaterialTheme.colorScheme.onSurface,
						letterSpacing = 0.25.sp,
					)
				)

				Text(
					text = music.album.releaseDate,
					style = TextStyle(
						fontSize = 14.sp,
						lineHeight = 20.sp,
						fontWeight = FontWeight(400),
						color = MaterialTheme.colorScheme.onSurfaceVariant,
						letterSpacing = 0.25.sp,
					)
				)
			}
		}
	)
}