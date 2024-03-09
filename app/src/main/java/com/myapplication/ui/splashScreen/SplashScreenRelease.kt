package com.myapplication.ui.splashScreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.myapplication.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenRelease(
	onSplashScreenFinished: () -> Unit
){
	SplashScreen(
		logo =  painterResource(id = R.drawable.logo),
		logoDescription = stringResource(id = R.string.application_logo),
		colorText = MaterialTheme.colorScheme.onBackground,
		buildType = stringResource(id = R.string.application_build_release),
		buildVersion = "1.0.0"
	)

	LaunchedEffect(key1 = true) {
		delay(2000)
		onSplashScreenFinished()
	}
}