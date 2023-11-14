package com.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.myapplication.ui.MusicalIcons
import com.myapplication.ui.MusicListView
import com.myapplication.ui.PlaylistListView
import com.myapplication.ui.ReaderView
import com.myapplication.ui.SettingsView
import com.myapplication.ui.components.MusicTopNavBar

@Composable
fun MusicalApp(
    loginViewModel: LoginViewModel,
    playlistViewModel: PlaylistViewModel,
    navigateToDetail: (Long) -> Unit = {}
) {
    val token by loginViewModel.spotifyTokenLiveData.observeAsState()

//    LaunchedEffect(Unit){
//        loginViewModel.fetchSpotifyToken()
//    }
    MusicalAppContent(
        playlistViewModel = playlistViewModel,
        navigateToDetail = navigateToDetail
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicalAppContent(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    navigateToDetail: (Long) -> Unit,
) {

    val selectedDestination = remember { mutableStateOf(MusicalRoute.READER) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        MusicTopNavBar(modifier = modifier,
            selectedDestination = selectedDestination)

        when(selectedDestination.value){
            MusicalRoute.READER -> {
                ReaderView(modifier = modifier.weight(1f))
            }
            MusicalRoute.PLAYLISTS -> {
                PlaylistListView(modifier = modifier.weight(1f), playlistViewModel, selectedDestination)
            }
            MusicalRoute.REMOVE_PLAYLIST -> {
                PlaylistListView(modifier = modifier.weight(1f), playlistViewModel, selectedDestination)
            }
            MusicalRoute.MUSICS -> {
                MusicListView(modifier = modifier.weight(1f), playlistViewModel, selectedDestination)
            }
            MusicalRoute.REMOVE_MUSICS -> {
                MusicListView(modifier = modifier.weight(1f), playlistViewModel, selectedDestination)
            }
            MusicalRoute.SETTINGS -> {
                SettingsView(modifier = modifier.weight(1f))
            }
        }

        NavigationBar(
            modifier = modifier.fillMaxWidth()
        ) {
            TOP_LEVEL_DESTINATIONS.forEach { musicalDestination ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = musicalDestination.selectedIcon,
                            contentDescription = stringResource(id = musicalDestination.iconTextId)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(musicalDestination.iconTextId)
                        )
                    },
                    selected = selectedDestination.value == musicalDestination.route,
                    onClick = { selectedDestination.value = musicalDestination.route }
                )
            }
        }
    }
}

object MusicalRoute {
    const val READER = "Reader"
    const val PLAYLISTS = "Playlists"
    const val REMOVE_PLAYLIST = "Remove"
    const val REMOVE_MUSICS = "Remove"
    const val MUSICS = "Musics"
    const val SETTINGS = "Settings"
}

data class MusicTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

val TOP_LEVEL_DESTINATIONS = listOf(
    MusicTopLevelDestination(
        route = MusicalRoute.READER,
        selectedIcon = MusicalIcons.iconReader,
        unselectedIcon = MusicalIcons.iconReader,
        iconTextId = R.string.tab_reader
    ),
    MusicTopLevelDestination(
        route = MusicalRoute.PLAYLISTS,
        selectedIcon = MusicalIcons.iconPlaylist,
        unselectedIcon = MusicalIcons.iconPlaylist,
        iconTextId = R.string.tab_playlists
    ),
    MusicTopLevelDestination(
        route = MusicalRoute.SETTINGS,
        selectedIcon = MusicalIcons.iconSettings,
        unselectedIcon = MusicalIcons.iconSettings,
        iconTextId = R.string.tab_settings
    )
)