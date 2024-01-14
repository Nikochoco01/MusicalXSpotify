package com.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.myapplication.dataSource.bluetooth.MusicalBluetoothManager
import com.myapplication.ui.views.LoginView
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.ui.views.MusicListView
import com.myapplication.ui.views.PlaylistListView
import com.myapplication.ui.views.ReaderView
import com.myapplication.ui.views.SettingsView
import com.myapplication.ui.views.SubscribeView
import com.myapplication.viewModels.BluetoothViewModel
import com.myapplication.viewModels.UsersViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier,
    navController: NavHostController,
    playlistViewModel: PlaylistViewModel,
    usersViewModel: UsersViewModel,
    bluetoothViewModel: BluetoothViewModel
){
    NavHost(navController = navController,
        startDestination = MusicalInternalAppRoute.Login.route,
        modifier = modifier){
        composable(MusicalInternalAppRoute.Login.route){
            LoginView(modifier, usersViewModel,
                onNavigateToSubscribe = {navController.navigate(MusicalInternalAppRoute.Subscribe.route)},
                onLoginSuccess = { navController.navigate(MusicalBarRoute.Reader.route){
                    popUpTo(MusicalInternalAppRoute.Login.route) { inclusive = true }
                } })
        }
        composable(MusicalInternalAppRoute.Subscribe.route){
            SubscribeView(modifier, usersViewModel,
                onNavigateToLogin = { navController.navigate(MusicalInternalAppRoute.Login.route)},
                onRegistrationSuccess = { navController.navigate(MusicalInternalAppRoute.Login.route){
                    popUpTo(MusicalInternalAppRoute.Subscribe.route) { inclusive = true }
                } })
        }
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
            SettingsView(modifier,
                bluetoothViewModel,
                onNavigate = { navController.navigate(MusicalInternalAppRoute.Login.route){
                    popUpTo(MusicalBarRoute.Reader.route) { inclusive = true }
                }
            })
            usersViewModel.fetchUserByCredential("", "")
        }
    }
}
