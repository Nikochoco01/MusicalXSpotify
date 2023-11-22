package com.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.myapplication.navigation.NavBottomBar
import com.myapplication.navigation.NavTopBar
import com.myapplication.navigation.NavigationGraph

@Composable
fun MusicalApp(
    loginViewModel: LoginViewModel,
    playlistViewModel: PlaylistViewModel
) {
    val navController = rememberNavController();
    val token by loginViewModel.spotifyTokenLiveData.observeAsState()

//    LaunchedEffect(Unit){
//        loginViewModel.fetchSpotifyToken()
//    }
    MusicalAppContent(
        playlistViewModel = playlistViewModel,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicalAppContent(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    navController: NavHostController
) {
    Scaffold (
        topBar = {NavTopBar(modifier, navController)},
        content = {paddingValues ->
            Box(modifier.fillMaxSize().padding(paddingValues) ){
                NavigationGraph(modifier, navController, playlistViewModel)
            } },
        bottomBar = {NavBottomBar(modifier, navController)}
    )
}
