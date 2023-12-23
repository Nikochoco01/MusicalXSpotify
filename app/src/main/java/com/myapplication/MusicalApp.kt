package com.myapplication

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.myapplication.dataSource.bluetooth.MusicalBluetoothManager
import com.myapplication.navigation.MusicalBarRoute
import com.myapplication.navigation.MusicalInternalAppRoute
import com.myapplication.navigation.NavBottomBar
import com.myapplication.navigation.NavTopBar
import com.myapplication.navigation.NavigationGraph
import com.myapplication.repository.users.UserMusicalManager
import com.myapplication.viewModels.SpotifyAPIViewModel
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.viewModels.UsersViewModel

@Composable
fun MusicalApp(
    spotifyAPIViewModel: SpotifyAPIViewModel,
    playlistViewModel: PlaylistViewModel,
    usersViewModel: UsersViewModel,
    userMusicalManager: UserMusicalManager,
    musicalBluetoothManager: MusicalBluetoothManager
) {
    val navController = rememberNavController();
    val token by spotifyAPIViewModel.spotifyTokenLiveData.observeAsState()
    val userLogged by usersViewModel.musicalUsersAuthentication.observeAsState(initial = null)

    if(userLogged != null){
        userMusicalManager.isConnected = true
        userMusicalManager.userID = userLogged!!.id!!
        navController.navigate(MusicalBarRoute.Reader.route){
            popUpTo(MusicalInternalAppRoute.Login.route) { inclusive = true }
        }
    }

    if(musicalBluetoothManager.bluetoothAdapter == null)
        Toast.makeText(LocalContext.current, "Your device don't support bluetooth", Toast.LENGTH_LONG).show()

    LaunchedEffect(Unit){
//        spotifyAPIViewModel.fetchSpotifyToken()
        usersViewModel.fetchUserByCredential("" , "")
    }
    MusicalAppContent(
        playlistViewModel = playlistViewModel,
        usersViewModel = usersViewModel,
        navController = navController,
        userMusicalManager = userMusicalManager,
        musicalBluetoothManager = musicalBluetoothManager
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicalAppContent(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    usersViewModel: UsersViewModel,
    navController: NavHostController,
    userMusicalManager: UserMusicalManager,
    musicalBluetoothManager: MusicalBluetoothManager
) {
    Scaffold (
        topBar = {
            if(userMusicalManager.isConnected)
                NavTopBar(modifier, navController)
        },
        content = {paddingValues ->
            Box(
                modifier
                    .fillMaxSize()
                    .padding(paddingValues) ){
                NavigationGraph(modifier, navController, musicalBluetoothManager, playlistViewModel, usersViewModel)
            } },
        bottomBar = {
            if(userMusicalManager.isConnected)
                NavBottomBar(modifier, navController, userMusicalManager.userID)
        }
    )
}
