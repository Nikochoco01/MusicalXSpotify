package com.myapplication.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.myapplication.PlaylistViewModel
import com.myapplication.ui.MusicListView
import com.myapplication.ui.PlaylistListView
import com.myapplication.ui.ReaderView
import com.myapplication.ui.SettingsView

@Composable
fun NavigationGraph(
    modifier: Modifier,
    navController: NavHostController,
    playlistViewModel: PlaylistViewModel
){
    NavHost(navController = navController,
        startDestination = MusicalBarRoute.Reader.route,
        modifier = modifier){
        composable(MusicalBarRoute.Reader.route){
            ReaderView(modifier)
        }
        composable(MusicalBarRoute.Playlist.route,
                    arguments = listOf(navArgument("userID"){type = NavType.StringType})){
                backStackEntry -> backStackEntry.arguments?.getString("userID")
            ?.let { PlaylistListView(modifier, playlistViewModel, navController, it) }
        }
        composable(MusicalBarRoute.PlaylistRemove.route,
                    arguments = listOf(navArgument("userID"){type = NavType.StringType})){
                backStackEntry -> backStackEntry.arguments?.getString("userID")
            ?.let { PlaylistListView(modifier, playlistViewModel, navController, it) }
        }
        composable(MusicalPlaylistDetails.Music.route,
                    arguments = listOf(navArgument("playlistID") { type = NavType.IntType })){
                backStackEntry -> backStackEntry.arguments?.getString("playlistID")
            ?.let { MusicListView(modifier, playlistViewModel, navController, it.toInt()) }
        }
        composable(MusicalPlaylistDetails.MusicRemove.route,
                    arguments = listOf(navArgument("playlistID") { type = NavType.IntType })){
                backStackEntry -> backStackEntry.arguments?.getString("playlistID")
            ?.let { MusicListView(modifier, playlistViewModel, navController, it.toInt()) }
        }
        composable(MusicalBarRoute.Settings.route){
            SettingsView(modifier)
        }
    }
}
