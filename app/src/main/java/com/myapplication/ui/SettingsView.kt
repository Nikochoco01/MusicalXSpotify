package com.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsView(
    modifier: Modifier = Modifier
){
    Column (
        modifier.fillMaxSize()
    ){
        Text(text= "Test Settings")
    }
}

@Preview
@Composable
fun SettingsPreview(){
    SettingsView()
}
