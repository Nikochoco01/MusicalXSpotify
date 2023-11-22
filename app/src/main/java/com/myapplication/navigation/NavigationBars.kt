package com.myapplication.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.myapplication.ui.MusicalIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavTopBar(modifier: Modifier, navController: NavHostController){
    val backStackEntry = navController.currentBackStackEntryAsState()
    CenterAlignedTopAppBar(modifier = modifier.background(MaterialTheme.colorScheme.surface),
        title = { backStackEntry.value?.destination?.route?.let { Text(it) } },
        navigationIcon = {
            when(backStackEntry.value?.destination?.route){
                MusicalRoute.REMOVE_PLAYLIST -> {
                    IconButton(onClick = { navController.navigate(MusicalBarRoute.Playlist.route)}) {
                        androidx.compose.material3.Icon(
                            imageVector = MusicalIcons.iconBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalRoute.REMOVE_MUSICS -> {
                    IconButton(onClick = { navController.navigate(MusicalPlaylistDetails.Music.route)}) {
                        androidx.compose.material3.Icon(
                            imageVector = MusicalIcons.iconBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            }
        },
        actions = {
            when(backStackEntry.value?.destination?.route){
                MusicalRoute.PLAYLISTS -> {
                    IconButton(onClick = { navController.navigate(MusicalBarRoute.PlaylistRemove.route) }) {
                        androidx.compose.material3.Icon(
                            imageVector = MusicalIcons.iconMenuVert,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalRoute.MUSICS -> {
                    IconButton(onClick = { navController.navigate(MusicalPlaylistDetails.MusicRemove.route) }) {
                        androidx.compose.material3.Icon(
                            imageVector = MusicalIcons.iconDelete,
                            contentDescription = "Localized description"
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun NavBottomBar(modifier: Modifier, navController: NavHostController){
    val appScreens = listOf(
        MusicalBarRoute.Reader,
        MusicalBarRoute.Playlist,
        MusicalBarRoute.Settings
    )
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        appScreens.forEach { musicalDestination ->
            NavigationBarItem(
                icon = { Icon(musicalDestination.selectedIcon, musicalDestination.route) },
                label = { Text(text = musicalDestination.route)},
                selected = musicalDestination.route == backStackEntry.value?.destination?.route,
                onClick = {navController.navigate(musicalDestination.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                } }
            )
        }
    }
}
