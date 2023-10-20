package com.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.myapplication.model.accessToken.AccessToken
import com.myapplication.ui.PlaylistView
import com.myapplication.ui.ReaderView
import com.myapplication.ui.SettingsView

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
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Localized description"
                    )
                }
            }
        )

        when(selectedDestination.value){
            MusicalRoute.READER -> {
                ReaderView(modifier = modifier.weight(1f))
            }
            MusicalRoute.PLAYLIST -> {
                PlaylistView(modifier = modifier.weight(1f), playlistViewModel)
            }
            MusicalRoute.SETTINGS -> {
                SettingsView(modifier = modifier.weight(1f))
            }
        }

        NavigationBar(
            modifier = modifier.fillMaxWidth()
        ) {
            TOP_LEVEL_DESTINATIONS.forEach { replyDestination ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = replyDestination.selectedIcon,
                            contentDescription = stringResource(id = replyDestination.iconTextId)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(replyDestination.iconTextId)
                        )
                    },
                    selected = selectedDestination.value == replyDestination.route,
                    onClick = { selectedDestination.value = replyDestination.route }
                )
            }
        }
    }
}

object MusicalRoute {
    const val READER = "Reader"
    const val PLAYLIST = "Playlist"
    const val SETTINGS = "Settings"
}

data class ReplyTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

val TOP_LEVEL_DESTINATIONS = listOf(
    ReplyTopLevelDestination(
        route = MusicalRoute.READER,
        selectedIcon = Icons.Default.GraphicEq,
        unselectedIcon = Icons.Default.GraphicEq,
        iconTextId = R.string.tab_reader
    ),
    ReplyTopLevelDestination(
        route = MusicalRoute.PLAYLIST,
        selectedIcon = Icons.Default.LibraryMusic,
        unselectedIcon = Icons.Default.LibraryMusic,
        iconTextId = R.string.tab_playlist
    ),
    ReplyTopLevelDestination(
        route = MusicalRoute.SETTINGS,
        selectedIcon = Icons.Outlined.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        iconTextId = R.string.tab_settings
    )
)