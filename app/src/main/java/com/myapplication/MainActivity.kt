package com.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.myapplication.dataSource.bluetooth.MusicalBluetoothService
import com.myapplication.repository.users.UserMusicalManager
import com.myapplication.ui.theme.MusicalXSpotifyTheme
import com.myapplication.viewModels.BluetoothViewModel
import com.myapplication.viewModels.SpotifyAPIViewModel
import com.myapplication.viewModels.PlaylistViewModel
import com.myapplication.viewModels.UsersViewModel

class MainActivity : ComponentActivity() {
    private val spotifyAPIViewModel : SpotifyAPIViewModel by viewModels()
    private val playlistViewModel : PlaylistViewModel by viewModels()
    private val usersViewModel : UsersViewModel by viewModels()
    private val bluetoothViewModel : BluetoothViewModel by viewModels()
    private val userMusicalManager = UserMusicalManager.getInstance()
    private val musicalBluetoothManager = MusicalBluetoothService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        musicalBluetoothManager.initPermission(this)

        setContent {
            MusicalXSpotifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF6650a4)
                ) {
                    MusicalApp(
                        spotifyAPIViewModel,
                        playlistViewModel,
                        usersViewModel,
                        userMusicalManager,
                        bluetoothViewModel
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        var socket = bluetoothViewModel.bluetoothSocket.value
        if(socket != null ){
            bluetoothViewModel.isConnected(socket)
            if(bluetoothViewModel.bluetoothSocketIsConnected.value == true)
                bluetoothViewModel.closeBluetoothSocket(socket)
        }
    }
}