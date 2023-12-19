package com.myapplication.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapplication.navigation.MusicalBarRoute
import com.myapplication.navigation.MusicalInternalAppRoute
import com.myapplication.ui.utils.MusicalIcons
import com.myapplication.viewModels.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    modifier: Modifier,
    usersViewModel: UsersViewModel,
    onNavigateToSubscribe: () -> Unit,
    onLoginSuccess: () -> Unit
){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val userLogged by usersViewModel.musicalUsersLiveDataNotResponse.observeAsState(initial = null)

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
		Text(MusicalInternalAppRoute.Login.routeName)
        Column (modifier.fillMaxWidth().height(160.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier.fillMaxWidth(),
                label = { Text(text = "Email")},
                placeholder = { Text(text = "Enter your email") }
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier.fillMaxWidth(),
                label = { Text(text = "Password")},
                placeholder = { Text(text = "Enter your password") }
            )
        }
        Row (modifier.fillMaxWidth().height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            OutlinedButton(onClick = { onNavigateToSubscribe.invoke() }, modifier.width(144.dp).height(48.dp)) {
                Icon(imageVector = MusicalIcons.iconAdd, contentDescription = "Add icon")
                Text(text = "Subscribe")
            }
            Button(onClick = {
                usersViewModel.fetchUserByCredential(email, password)
                onLoginSuccess.invoke()
            }, modifier.width(144.dp).height(48.dp)) {
                Icon(imageVector = MusicalIcons.iconLogin, contentDescription = "Login icon")
                Text(text = "Login")
            }
        }
    }
}