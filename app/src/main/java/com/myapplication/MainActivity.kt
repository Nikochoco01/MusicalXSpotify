package com.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.myapplication.repository.users.UserMusicalManager
import com.myapplication.ui.theme.MusicalXSpotifyTheme
import com.myapplication.viewModels.SpotifyAPIViewModel
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.viewModels.UsersViewModel
import com.myapplication.ui.splashScreen.SplashScreenDebug
import com.myapplication.ui.splashScreen.SplashScreenRelease

class MainActivity : ComponentActivity() {
    private val spotifyAPIViewModel : SpotifyAPIViewModel by viewModels()
    private val playlistViewModel : PlaylistViewModel by viewModels()
    private val usersViewModel : UsersViewModel by viewModels()
    private val userMusicalManager = UserMusicalManager.getInstance()
    private val isDevelopBuild: Boolean = BuildConfig.IS_DEVELOP_BUILD
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            MusicalXSpotifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MusicalApp(
                        spotifyAPIViewModel,
                        playlistViewModel,
                        usersViewModel,
                        userMusicalManager
                    )
//                    var showSplashScreen: Boolean by remember{ mutableStateOf(true) }
//
//                    if (showSplashScreen) {
//                        if(isDevelopBuild) {
//                            SplashScreenDebug {
//                                showSplashScreen = false
//                            }
//                        }
//                        else {
//                            SplashScreenRelease {
//                                showSplashScreen = false
//                            }
//                        }
//                    }
//                    else{
//
//                    }
                }
            }
        }
    }
}