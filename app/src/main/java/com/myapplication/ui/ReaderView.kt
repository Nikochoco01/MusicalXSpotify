package com.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapplication.R
import com.myapplication.ui.components.MusicPicture

@Composable
fun ReaderView(
    modifier: Modifier = Modifier
){
    Column (
        modifier.fillMaxSize().padding(16.dp)
    ){
        ElevatedCard(
            modifier.fillMaxWidth().height(440.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            MusicPicture(drawableResource = R.drawable.picture_1 , description = "Music picture")
            Column {
                Text(text = "Title")
                Text(text = "Artist")
            }
        }

        Column(
            modifier.fillMaxWidth()) {
            var sliderPosition by remember { mutableFloatStateOf(0f) }
            var inRead by remember { mutableStateOf(false) }
            Column (
                modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "00:00:00")
                    Text(text = "00:00:00")
                }
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    modifier.width(248.dp)
                )
            }
            Row (
                modifier.fillMaxWidth().fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(MusicalIcons.iconPrevious, "previous button")
                }
                IconToggleButton(
                    checked = inRead,
                    onCheckedChange = {
                        inRead = it
                    },
                    modifier.clip(RoundedCornerShape(56.dp)),
                    colors = IconButtonDefaults.filledIconToggleButtonColors(
                                                    checkedContainerColor = MaterialTheme.colorScheme.primary)
                ) {
                    var icon = if (inRead) MusicalIcons.iconPause else MusicalIcons.iconPlay
                    Icon(icon, "play/pause button")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(MusicalIcons.iconNext, "next button")
                }
            }
        }
    }
}

@Preview
@Composable
fun ReaderPreview(){
    ReaderView()
}
