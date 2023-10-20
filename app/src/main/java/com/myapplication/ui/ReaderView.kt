package com.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ReaderView(
    modifier: Modifier = Modifier
){
    Column (
        modifier.fillMaxSize()
    ){
        Text(text= "Test Reader")
    }
}

@Preview
@Composable
fun ReaderPreview(){
    ReaderView()
}
