package com.myapplication.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.myapplication.ui.views.LoginView
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.ui.views.MusicListView
import com.myapplication.ui.views.PlaylistListView
import com.myapplication.ui.views.ReaderView
import com.myapplication.ui.views.SettingsView
import com.myapplication.ui.views.SubscribeView
import com.myapplication.viewModels.SpotifyAPIViewModel
import com.myapplication.viewModels.UsersViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier,
    navController: NavHostController,
    playlistViewModel: PlaylistViewModel,
    usersViewModel: UsersViewModel,
    spotifyAPIViewModel: SpotifyAPIViewModel
){
    var spotifyToken = spotifyAPIViewModel.spotifyTokenLiveData.observeAsState()

    LaunchedEffect(spotifyToken){
        if(spotifyToken.value != null){
            Log.e("error", "SpotifyToken ${spotifyAPIViewModel.spotifyTokenLiveData.value}")
            navController.navigate(MusicalBarRoute.Reader.route){
            popUpTo(MusicalInternalAppRoute.Login.route) { inclusive = true } }
        }
    }

    NavHost(navController = navController,
        startDestination = MusicalInternalAppRoute.Login.route,
        modifier = modifier){
        composable(MusicalInternalAppRoute.Login.route){
            LoginView(modifier, usersViewModel,
                onNavigateToSubscribe = {navController.navigate(MusicalInternalAppRoute.Subscribe.route)},
                onLoginSuccess = { userLogged ->
                    if (userLogged != null) {
                        if(userLogged.spotifyUsersID != null){
                            Log.e("error", "call spotify")
//                            spotifyAPIViewModel.fetchSpotifyToken()
                        }
                        Log.e("error", "OnLoginSuccess")
                    }
                }
            )
            Log.e("error", "Call composable")
        }
        composable(MusicalInternalAppRoute.Subscribe.route){
            SubscribeView(modifier, usersViewModel,
                onNavigateToLogin = { navController.navigate(MusicalInternalAppRoute.Login.route)},
                onRegistrationSuccess = { navController.navigate(MusicalInternalAppRoute.Login.route){
                    popUpTo(MusicalInternalAppRoute.Subscribe.route) { inclusive = true }
                } }
            )
        }
//        composable(MusicalBarRoute.Reader.route){
//            ReaderView(modifier)
//        }
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
            SettingsView(modifier,
                usersViewModel = usersViewModel,
                spotifyAPIViewModel = spotifyAPIViewModel,
                onNavigate = { navController.navigate(MusicalInternalAppRoute.Login.route){
                    popUpTo(MusicalBarRoute.Reader.route) { inclusive = true }
                }
            })
        }
    }
}
