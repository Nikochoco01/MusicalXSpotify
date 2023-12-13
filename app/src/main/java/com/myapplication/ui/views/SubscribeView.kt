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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapplication.navigation.MusicalBarRoute
import com.myapplication.navigation.MusicalInternalAppRoute
import com.myapplication.ui.utils.MusicalIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  SubscribeView(
    modifier: Modifier,
    navController: NavController
){
    var pseudo by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(modifier.fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(MusicalInternalAppRoute.Subscribe.routeName)
        Column (modifier.fillMaxWidth().height(248.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(
                value = pseudo,
                onValueChange = { pseudo = it },
                modifier.fillMaxWidth(),
                label = { Text(text = "Pseudo")},
                placeholder = { Text(text = "Enter your pseudo") }
            )
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
            OutlinedButton(onClick = { navController.navigate(MusicalInternalAppRoute.Login.route) }, modifier.width(144.dp).height(48.dp)) {
                Icon(imageVector = MusicalIcons.iconClose, contentDescription = "Cancel icon")
                Text(text = "Cancel")
            }
            Button(onClick = {
                val isSubscribe = true
                if(isSubscribe){
                    navController.navigate(MusicalBarRoute.Reader.route)
                }
            }, modifier.width(144.dp).height(48.dp)) {
                Icon(imageVector = MusicalIcons.iconAdd, contentDescription = "Subscribe icon")
                Text(text = "Subscribe")
            }
        }
    }
}