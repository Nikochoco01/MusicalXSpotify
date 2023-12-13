package com.myapplication

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
import com.myapplication.navigation.NavBottomBar
import com.myapplication.navigation.NavTopBar
import com.myapplication.navigation.NavigationGraph
import com.myapplication.viewModels.SpotifyAPIViewModel
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.viewModels.UsersViewModel

@Composable
fun MusicalApp(
    spotifyAPIViewModel: SpotifyAPIViewModel,
    playlistViewModel: PlaylistViewModel,
    usersViewModel: UsersViewModel
) {
    var isConnected = false
    val navController = rememberNavController();
    val token by spotifyAPIViewModel.spotifyTokenLiveData.observeAsState()
    val userLogged by usersViewModel.musicalUsersLiveDataNotResponse.observeAsState(initial = null)
    if(userLogged != null)
        isConnected = true
    LaunchedEffect(Unit){
//        spotifyAPIViewModel.fetchSpotifyToken()
        usersViewModel.FetchUserByCredential("" , "")
    }
    MusicalAppContent(
        playlistViewModel = playlistViewModel,
        usersViewModel = usersViewModel,
        navController = navController,
        isConnected = isConnected
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicalAppContent(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    usersViewModel: UsersViewModel,
    navController: NavHostController,
    isConnected: Boolean
) {
    Scaffold (
        topBar = {
            if(isConnected)
                NavTopBar(modifier, navController)
        },
        content = {paddingValues ->
            Box(
                modifier
                    .fillMaxSize()
                    .padding(paddingValues) ){
                NavigationGraph(modifier, navController, playlistViewModel, usersViewModel)
            } },
        bottomBar = {
            if(isConnected)
                NavBottomBar(modifier, navController, 1)
        }
    )
}
