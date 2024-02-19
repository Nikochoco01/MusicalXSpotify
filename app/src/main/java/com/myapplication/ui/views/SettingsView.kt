package com.myapplication.ui.views

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapplication.R
import com.myapplication.model.users.MusicalUsers
import com.myapplication.ui.components.MusicalSettingItem
import com.myapplication.ui.components.SpotifyDialog
import com.myapplication.ui.utils.MusicalIcons
import com.myapplication.viewModels.SpotifyAPIViewModel
import com.myapplication.viewModels.UsersViewModel

@Composable
fun SettingsView(
    modifier: Modifier = Modifier,
    spotifyAPIViewModel: SpotifyAPIViewModel,
    usersViewModel: UsersViewModel,
    onNavigate: () -> Unit
){
    val userLogged = usersViewModel.musicalUsersLiveData.observeAsState(initial = null)
    val userUpdated = usersViewModel.musicalUsersUpdated.observeAsState(initial = false)
    val showSpotifyDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit){
        usersViewModel.fetchMusicalUserByMusicalID(1)
    }
    Column (
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        MusicalSettingItem(modifier = modifier,
            textItem = R.string.setting_account,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            icon = MusicalIcons.iconArrowRight,
            iconDescription = R.string.icon_arrow_right,
            onClickable = {}
        )
        MusicalSettingItem(modifier = modifier,
            textItem = R.string.setting_account_spotify,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            icon = MusicalIcons.iconArrowRight,
            iconDescription = R.string.icon_arrow_right,
            onClickable = {showSpotifyDialog.value = true}
        )
        if(showSpotifyDialog.value){
            SpotifyDialog(modifier = modifier,
                onSaveRequest = {spotifyUserID ->
                    if(userLogged.value != null){
                        userLogged.value?.spotifyUsersID = spotifyUserID
                        usersViewModel.updateMusicalUser(userLogged.value!!)
                        Log.e("error", "Update Value ${userUpdated.value}")
                        if(userUpdated.value){
                            showSpotifyDialog.value = false
                        }
                    }
                },
                onDismissRequest = {showSpotifyDialog.value = false})
        }
        MusicalSettingItem(modifier = modifier,
            textItem = R.string.setting_bluetooth,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            icon = MusicalIcons.iconArrowRight,
            iconDescription = R.string.icon_arrow_right,
            onClickable = {}
        )
        if(isSystemInDarkTheme())
            MusicalSettingItem(modifier = modifier,
                textItem = R.string.setting_Light_mode,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                icon = MusicalIcons.iconLightMode,
                iconDescription = R.string.icon_light_mode,
                onClickable = {}
            )
        else
            MusicalSettingItem(modifier = modifier,
                textItem = R.string.setting_Dark_mode,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                icon = MusicalIcons.iconDarkMode,
                iconDescription = R.string.icon_dark_mode,
                onClickable = {}
            )
        MusicalSettingItem(modifier = modifier,
            textItem = R.string.setting_Logout,
            textColor = MaterialTheme.colorScheme.onErrorContainer,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            icon = MusicalIcons.iconLogout,
            iconDescription = R.string.icon_logout,
            onClickable = {onNavigate.invoke()}
        )
    }
}