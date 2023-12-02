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
        title = {
            var text = when(backStackEntry.value?.destination?.route){
                MusicalInternalAppRoute.RemovePlaylist.route -> MusicalInternalAppRoute.RemovePlaylist.routeName
                MusicalInternalAppRoute.RemoveMusic.route -> MusicalInternalAppRoute.RemoveMusic.routeName
                MusicalInternalAppRoute.LoadPlaylist.route -> MusicalInternalAppRoute.LoadPlaylist.routeName
                MusicalBarRoute.Reader.route -> MusicalBarRoute.Reader.routeName
                MusicalBarRoute.Playlist.route -> MusicalBarRoute.Playlist.routeName
                MusicalBarRoute.Settings.route -> MusicalBarRoute.Settings.routeName
                else -> "Title no undefined"
            }
            Text(text)
                },
        navigationIcon = {
            when(backStackEntry.value?.destination?.route){
                MusicalInternalAppRoute.RemovePlaylist.route -> {
                    IconButton(onClick = { navController.navigate(MusicalBarRoute.Playlist.route)}) {
                        Icon(
                            imageVector = MusicalIcons.iconBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalInternalAppRoute.RemoveMusic.route -> {
                    IconButton(onClick = {
                        var playlistID = backStackEntry?.value?.arguments?.getString("playlistID").toString()
                        navController.navigate(MusicalInternalAppRoute.LoadPlaylist.route.replace("{playlistID}", playlistID))
                    }) {
                        Icon(
                            imageVector = MusicalIcons.iconBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalInternalAppRoute.LoadPlaylist.route -> {
                    IconButton(onClick = { navController.navigate(MusicalBarRoute.Playlist.route)}) {
                        Icon(
                            imageVector = MusicalIcons.iconBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            }
        },
        actions = {
            when(backStackEntry.value?.destination?.route){
                MusicalBarRoute.Playlist.route -> {
                    IconButton(onClick = { navController.navigate(MusicalInternalAppRoute.RemovePlaylist.route) }) {
                        Icon(
                            imageVector = MusicalIcons.iconMenuVert,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalInternalAppRoute.LoadPlaylist.route -> {
                    IconButton(onClick = {
                            var playlistID = backStackEntry?.value?.arguments?.getString("playlistID").toString()
                            navController.navigate(MusicalInternalAppRoute.RemoveMusic.route.replace("{playlistID}", playlistID))
                    }) {
                        Icon(
                            imageVector = MusicalIcons.iconMenuVert,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalInternalAppRoute.RemovePlaylist.route -> {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = MusicalIcons.iconDelete,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalInternalAppRoute.RemoveMusic.route -> {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
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
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        val backStackEntry = navController.currentBackStackEntryAsState()

        // Reader Route
        NavigationBarItem(
            selected = MusicalBarRoute.Reader.route == backStackEntry.value?.destination?.route,
            onClick = {navController.navigate(MusicalBarRoute.Reader.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            } },
            icon = {
                val icon = if (MusicalBarRoute.Reader.route == backStackEntry.value?.destination?.route)
                    MusicalBarRoute.Reader.selectedIcon
                else
                    MusicalBarRoute.Reader.unselectedIcon
                Icon(icon, MusicalBarRoute.Reader.route)
            },
            label = { Text(text = MusicalBarRoute.Reader.routeName)}
        )

        // Playlists Route
        NavigationBarItem(
            selected = when(backStackEntry.value?.destination?.route){
                    MusicalBarRoute.Playlist.route -> true
                    MusicalInternalAppRoute.LoadPlaylist.route -> true
                    MusicalInternalAppRoute.RemovePlaylist.route -> true
                    MusicalInternalAppRoute.RemoveMusic.route -> true
                else -> false
            },
            onClick = {navController.navigate("user/1/playlists"){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            } },
            icon = {
                val icon = when(backStackEntry.value?.destination?.route){
                    MusicalBarRoute.Playlist.route -> MusicalBarRoute.Playlist.selectedIcon
                    MusicalInternalAppRoute.RemovePlaylist.route -> MusicalBarRoute.Playlist.selectedIcon
                    MusicalInternalAppRoute.LoadPlaylist.route -> MusicalBarRoute.Playlist.selectedIcon
                    MusicalInternalAppRoute.RemoveMusic.route -> MusicalBarRoute.Playlist.selectedIcon
                    else -> MusicalBarRoute.Playlist.unselectedIcon}
                Icon(icon, MusicalBarRoute.Playlist.route)
            },
            label = { Text(text = MusicalBarRoute.Playlist.routeName)}
        )

        // Settings Route
        NavigationBarItem(
            selected = MusicalBarRoute.Settings.route == backStackEntry.value?.destination?.route,
            onClick = {navController.navigate(MusicalBarRoute.Settings.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            } },
            icon = {
                val icon = if (MusicalBarRoute.Settings.route == backStackEntry.value?.destination?.route)
                    MusicalBarRoute.Settings.selectedIcon
                else
                    MusicalBarRoute.Settings.unselectedIcon
                Icon(icon, MusicalBarRoute.Settings.route)
            },
            label = { Text(text = MusicalBarRoute.Settings.routeName)}
        )
    }
}
