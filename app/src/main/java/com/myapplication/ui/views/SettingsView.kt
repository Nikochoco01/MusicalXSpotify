package com.myapplication.ui.views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapplication.R
import com.myapplication.ui.components.BluetoothDialog
import com.myapplication.ui.components.MusicalSettingItem
import com.myapplication.ui.utils.MusicalIcons
import com.myapplication.viewModels.BluetoothViewModel

@Composable
fun SettingsView(
    modifier: Modifier = Modifier,
    bluetoothViewModel: BluetoothViewModel,
    onNavigate: () -> Unit
){
    val showBluetoothDialog = remember {mutableStateOf(false)}
    val state by bluetoothViewModel.state.collectAsState()
    Column (
        modifier.fillMaxSize().padding(16.dp),
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
            onClickable = {}
        )
        MusicalSettingItem(modifier = modifier,
            textItem = R.string.setting_bluetooth,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            icon = MusicalIcons.iconArrowRight,
            iconDescription = R.string.icon_arrow_right,
            onClickable = {showBluetoothDialog.value = true}
        )

        if(showBluetoothDialog.value){
            BluetoothDialog (
                state = state,
                onDismissRequest = {showBluetoothDialog.value = false},
                onStartScan = {bluetoothViewModel.startScan()},
                onStopScan = {bluetoothViewModel.stopScan()}
            )
        }

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
