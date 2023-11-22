package com.myapplication.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapplication.R
import com.myapplication.model.MusicalPlaylists
import com.myapplication.navigation.MusicalBarRoute
import com.myapplication.navigation.MusicalPlaylistDetails

@Composable
fun PlaylistListItem(
    playlist: MusicalPlaylists,
    navController: NavController,
    modifier: Modifier = Modifier
){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 160.dp, height = 224.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate(MusicalPlaylistDetails.Music.route + "/" + playlist.id)
            }
    ) {
        Column(
            modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally) {
            PlaylistPicture(drawableResource = R.drawable.picture_1, description = "playlist picture")
            Text(
                text = playlist.name,
                fontSize = 24.sp,
                letterSpacing = 0.5.sp,
                lineHeight = 24.sp
            )
        }
    }
}