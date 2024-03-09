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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.myapplication.R
import com.myapplication.navigation.MusicalInternalAppRoute
import com.myapplication.ui.utils.MusicalIcons
import com.myapplication.viewModels.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  SubscribeView(
    modifier: Modifier,
    usersViewModel: UsersViewModel,
    onNavigateToLogin: () -> Unit,
    onRegistrationSuccess: () -> Unit
){
    var pseudo by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(MusicalInternalAppRoute.Subscribe.routeName)
        Column (
            modifier
                .fillMaxWidth()
                .height(248.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(
                value = pseudo,
                onValueChange = { pseudo = it },
                modifier.fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.label_input_pseudo))},
                placeholder = { Text(text = stringResource(id = R.string.placeholder_input_pseudo)) }
            )
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
            OutlinedButton(onClick = { onNavigateToLogin.invoke() },
                modifier
                    .width(144.dp)
                    .height(48.dp)) {
                Icon(imageVector = MusicalIcons.iconClose, contentDescription = stringResource(id = R.string.icon_cancel))
                Text(text = stringResource(id = R.string.action_close_dialog))
            }
            Button(
                onClick = {
                    usersViewModel.createMusicalUser(pseudo, email, password)
                    onRegistrationSuccess.invoke()
                },
                modifier
                    .width(144.dp)
                    .height(48.dp)
            ) {
                Icon(imageVector = MusicalIcons.iconAdd, contentDescription = stringResource(id = R.string.icon_subscribe))
                Text(text = stringResource(id = R.string.action_subscribe))
            }
        }
    }
}