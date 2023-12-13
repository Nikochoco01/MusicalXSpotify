package com.myapplication.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.R
import com.myapplication.ui.components.MusicalPicture
import com.myapplication.ui.utils.MusicalIcons

@Composable
fun ReaderView(
    modifier: Modifier = Modifier
){
    Column (
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        ElevatedCard(
            modifier
                .fillMaxWidth()
                .height(440.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            MusicalPicture(
                modifier
                    .fillMaxWidth()
                    .height(328.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp)),
                drawableResource = R.drawable.picture_1_square , description = "Music picture")
            Spacer(modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(2.dp)
                .clip(
                    RoundedCornerShape(8.dp)
                )
                .background(MaterialTheme.colorScheme.primary))
            Column(
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceAround) {
                Text(text = "Title" , fontSize = 32.sp)
                Text(text = "Artist", fontSize = 16.sp)
            }
        }
        Spacer(modifier = modifier.height(8.dp))
        Column(
            modifier
                .fillMaxWidth()) {
            var sliderPosition by remember { mutableFloatStateOf(0f) }
            var inRead by remember { mutableStateOf(false) }
            Column (
                modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    modifier
                        .fillMaxWidth(),
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
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(onClick = { /*TODO*/ }, modifier.size(56.dp), colors = IconButtonDefaults.outlinedIconButtonColors(contentColor = MaterialTheme.colorScheme.secondary)) {
                    Icon(MusicalIcons.iconPrevious, "previous button", modifier.size(56.dp))
                }
                IconToggleButton(
                    checked = inRead,
                    onCheckedChange = {
                        inRead = it
                    },
                    modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(56.dp)),
                    colors = IconButtonDefaults.filledIconToggleButtonColors(
                                                    checkedContainerColor = MaterialTheme.colorScheme.primary)
                ) {
                    var icon = if (inRead) MusicalIcons.iconPause else MusicalIcons.iconPlay
                    Icon(icon, "play/pause button", modifier.size(64.dp))
                }
                IconButton(onClick = { /*TODO*/ }, modifier.size(56.dp), colors = IconButtonDefaults.outlinedIconButtonColors(contentColor = MaterialTheme.colorScheme.secondary)) {
                    Icon(MusicalIcons.iconNext, "next button", modifier.size(56.dp))
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
