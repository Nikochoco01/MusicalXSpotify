package com.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.myapplication.repository.users.UserMusicalManager
import com.myapplication.ui.theme.MusicalXSpotifyTheme
import com.myapplication.viewModels.PhoneManagerViewModel
import com.myapplication.viewModels.SpotifyAPIViewModel
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.viewModels.UsersViewModel

class MainActivity : ComponentActivity() {
    private val spotifyAPIViewModel : SpotifyAPIViewModel by viewModels()
    private val playlistViewModel : PlaylistViewModel by viewModels()
    private val usersViewModel : UsersViewModel by viewModels()
    private val phoneManagerViewModel: PhoneManagerViewModel by viewModels()
    private val userMusicalManager = UserMusicalManager.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        phoneManagerViewModel.initContext(this)
        phoneManagerViewModel.takePermission()
        phoneManagerViewModel.takePhoneFile()
        setContent {
            MusicalXSpotifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF6650a4)
                ) {
                    MusicalApp(
                        userMusicalManager,
                        spotifyAPIViewModel,
                        phoneManagerViewModel,
                        playlistViewModel,
                        usersViewModel
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicalXSpotifyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF6650a4)
        ) {
//            MusicalApp(loginViewModel = )
        }
    }
}