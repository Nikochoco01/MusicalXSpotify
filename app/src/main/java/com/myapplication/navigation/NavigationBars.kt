package com.myapplication.navigation

import android.util.Log
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
                        Icon(
                            imageVector = MusicalIcons.iconBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalRoute.REMOVE_MUSICS -> {
                    IconButton(onClick = { navController.navigate(MusicalPlaylistDetails.Music.route)}) {
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
                MusicalRoute.PLAYLISTS -> {
                    IconButton(onClick = { navController.navigate(MusicalBarRoute.PlaylistRemove.route) }) {
                        Icon(
                            imageVector = MusicalIcons.iconMenuVert,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalRoute.REMOVE_PLAYLIST -> {
                    IconButton(onClick = { navController.navigate(MusicalBarRoute.Playlist.route) }) {
                        Icon(
                            imageVector = MusicalIcons.iconDelete,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalRoute.MUSICS -> {
                    IconButton(onClick = {
                        Log.e("ROUTE" , backStackEntry.value?.destination?.route.toString())
                        backStackEntry.value?.arguments?.toString()?.let { Log.e("ROUTE" , it) }
                        Log.e("ROUTE" , MusicalPlaylistDetails.MusicRemove.route.toString())
                        navController.navigate(MusicalPlaylistDetails.MusicRemove.route)
                    }) {
                        Icon(
                            imageVector = MusicalIcons.iconMenuVert,
                            contentDescription = "Localized description"
                        )
                    }
                }
                MusicalRoute.REMOVE_MUSICS -> {
                    IconButton(onClick = {
                        Log.e("ROUTE" , backStackEntry.value?.destination?.route.toString())
                        backStackEntry.value?.arguments?.toString()?.let { Log.e("ROUTE" , it) }
                        //navController.navigate(MusicalPlaylistDetails.Music.route)
                        }) {
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
            icon = { Icon(MusicalBarRoute.Reader.selectedIcon, MusicalBarRoute.Reader.route) },
            label = { Text(text = MusicalBarRoute.Reader.routeName)},
            selected = MusicalBarRoute.Reader.route == backStackEntry.value?.destination?.route,
            onClick = {navController.navigate(MusicalBarRoute.Reader.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            } }
        )

        // Playlists Route
        NavigationBarItem(
            icon = { Icon(MusicalBarRoute.Playlist.selectedIcon, MusicalBarRoute.Playlist.route) },
            label = { Text(text = MusicalBarRoute.Playlist.routeName)},
            selected = MusicalBarRoute.Playlist.route == backStackEntry.value?.destination?.route,
            onClick = {navController.navigate("1/playlists"){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            } }
        )

        // Settings Route
        NavigationBarItem(
            icon = { Icon(MusicalBarRoute.Settings.selectedIcon, MusicalBarRoute.Settings.route) },
            label = { Text(text = MusicalBarRoute.Settings.routeName)},
            selected = MusicalBarRoute.Settings.route == backStackEntry.value?.destination?.route,
            onClick = {navController.navigate(MusicalBarRoute.Settings.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            } }
        )
    }
}
