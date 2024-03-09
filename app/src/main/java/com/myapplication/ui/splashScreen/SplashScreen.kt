package com.myapplication.ui.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashScreen(
	modifier: Modifier = Modifier,
	logo: Painter,
	logoDescription: String,
	colorText: Color,
	buildType: String,
	buildVersion: String,
){
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.SpaceBetween,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Column (modifier = modifier.fillMaxWidth()
		){
		}
		Image(painter = logo, contentDescription = logoDescription)
		Column (modifier = modifier
			.fillMaxWidth(),
			verticalArrangement = Arrangement.SpaceBetween,
			horizontalAlignment = Alignment.CenterHorizontally
		){
			Text(text = buildType, fontSize = 16.sp, color = colorText)
			Text(text = buildVersion, fontSize = 16.sp, color = colorText)
		}
	}
}