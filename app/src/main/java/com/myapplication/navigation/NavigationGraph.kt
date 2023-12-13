package com.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.ui.views.MusicListView
import com.myapplication.ui.views.PlaylistListView
import com.myapplication.ui.views.ReaderView
import com.myapplication.ui.views.SettingsView

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
                    ?.let { PlaylistListView(playlistViewModel, navController, it) }
        }
        composable(MusicalInternalAppRoute.RemovePlaylist.route,
            arguments = listOf(navArgument("userID"){type = NavType.StringType})){
                backStackEntry -> backStackEntry.arguments?.getString("userID")
                    ?.let { PlaylistListView(playlistViewModel, navController, it) }
        }
        composable(MusicalInternalAppRoute.LoadPlaylist.route,
            arguments = listOf(navArgument("playlistID"){type = NavType.StringType})){
               backStackEntry -> backStackEntry.arguments?.getString("playlistID")
                   ?.let { idObtained -> MusicListView(playlistViewModel, navController, idObtained.toInt())}
        }
        composable(MusicalInternalAppRoute.RemoveMusic.route,
            arguments = listOf(navArgument("playlistID"){type = NavType.StringType})){
                backStackEntry -> backStackEntry.arguments?.getString("playlistID")
            ?.let { idObtained -> MusicListView(playlistViewModel, navController, idObtained.toInt())}
        }
        composable(MusicalBarRoute.Settings.route){
            SettingsView(modifier)
        }
    }
}
