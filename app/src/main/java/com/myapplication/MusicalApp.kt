package com.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.myapplication.navigation.MusicalBarRoute
import com.myapplication.navigation.MusicalInternalAppRoute
import com.myapplication.navigation.NavBottomBar
import com.myapplication.navigation.NavTopBar
import com.myapplication.navigation.NavigationGraph
import com.myapplication.repository.users.UserMusicalManager
import com.myapplication.viewModels.PhoneManagerViewModel
import com.myapplication.viewModels.SpotifyAPIViewModel
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.viewModels.UsersViewModel

@Composable
fun MusicalApp(
    userMusicalManager: UserMusicalManager,
    spotifyAPIViewModel: SpotifyAPIViewModel,
    phoneManagerViewModel: PhoneManagerViewModel,
    playlistViewModel: PlaylistViewModel,
    usersViewModel: UsersViewModel
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
    LaunchedEffect(Unit){
//        spotifyAPIViewModel.fetchSpotifyToken()
        usersViewModel.fetchUserByCredential("" , "")
    }
    MusicalAppContent(
        navController = navController,
        userMusicalManager = userMusicalManager,
        phoneManagerViewModel = phoneManagerViewModel,
        playlistViewModel = playlistViewModel,
        usersViewModel = usersViewModel
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicalAppContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userMusicalManager: UserMusicalManager,
    phoneManagerViewModel: PhoneManagerViewModel,
    playlistViewModel: PlaylistViewModel,
    usersViewModel: UsersViewModel
) {

    Scaffold (
        topBar = {
            if(userMusicalManager.isConnected){
                NavTopBar(modifier, navController,
                    onPermission = { phoneManagerViewModel.getPermission() },
                    onImport = { phoneManagerViewModel.getPhoneFile() }
                )
            }
        },
        content = {paddingValues ->
            Box(
                modifier
                    .fillMaxSize()
                    .padding(paddingValues) ){
                NavigationGraph(modifier, navController, playlistViewModel, usersViewModel, phoneManagerViewModel)
            } },
        bottomBar = {
            if(userMusicalManager.isConnected)
                NavBottomBar(modifier, navController, userMusicalManager.userID)
        }
    )
}
