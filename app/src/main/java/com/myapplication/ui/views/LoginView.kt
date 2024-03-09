package com.myapplication.ui.views

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.myapplication.model.users.MusicalUsers
import com.myapplication.navigation.MusicalInternalAppRoute
import com.myapplication.ui.utils.MusicalIcons
import com.myapplication.viewModels.UsersViewModel
import com.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    modifier: Modifier,
    usersViewModel: UsersViewModel,
    onNavigateToSubscribe: () -> Unit,
    onLoginSuccess: (MusicalUsers?) -> Unit
){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val userLogged by usersViewModel.musicalUsersAuthentication.observeAsState()

    LaunchedEffect(userLogged){
        if(userLogged != null){
            if(userLogged?.mail == email && userLogged?.password == password){
                onLoginSuccess.invoke(userLogged)
            }
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
		Text(MusicalInternalAppRoute.Login.routeName)
        Column (
            modifier
                .fillMaxWidth()
                .height(160.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier.fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.label_input_mail))},
                placeholder = { Text(text = stringResource(id = R.string.placeholder_input_mail)) }
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier.fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.label_input_password))},
                placeholder = { Text(text = stringResource(id = R.string.placeholder_input_password)) }
            )
        }
        Row (
            modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            OutlinedButton(onClick = { onNavigateToSubscribe.invoke() },
                modifier
                    .width(144.dp)
                    .height(48.dp)) {
                Icon(imageVector = MusicalIcons.iconAdd, contentDescription = stringResource(id = R.string.icon_add))
                Text(text = stringResource(id = R.string.action_subscribe))
            }
            Button(onClick = {
                if(email.isNotBlank() && email.isNotEmpty() && password.isNotBlank() && password.isNotEmpty()){
                    usersViewModel.fetchUserByCredential(email, password)
                }
            },
                modifier
                    .width(144.dp)
                    .height(48.dp)) {
                Icon(imageVector = MusicalIcons.iconLogin, contentDescription = stringResource(id = R.string.icon_login))
                Text(text = stringResource(id = R.string.action_login))
            }
        }
    }
}