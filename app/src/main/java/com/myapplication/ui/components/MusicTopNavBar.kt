package com.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.myapplication.MusicalRoute
import com.myapplication.ui.MusicalIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicTopNavBar(
    modifier: Modifier,
    selectedDestination: MutableState<String>,
//    doRemove: Boolean
){
    CenterAlignedTopAppBar(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        title = {
            Text(
                text = selectedDestination.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if(selectedDestination.value == MusicalRoute.REMOVE_PLAYLIST){
                IconButton(onClick = { selectedDestination.value = MusicalRoute.PLAYLISTS}) {
                    Icon(
                        imageVector = MusicalIcons.iconBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        actions = {
            if(selectedDestination.value == MusicalRoute.PLAYLISTS){
                IconButton(onClick = { selectedDestination.value = MusicalRoute.REMOVE_PLAYLIST }) {
                    Icon(
                        imageVector = MusicalIcons.iconMenuVert,
                        contentDescription = "Localized description"
                    )
                }
            }
            else if(selectedDestination.value == MusicalRoute.REMOVE_MUSICS){
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = MusicalIcons.iconDelete,
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    )
}


