package com.myapplication.di

import MusicalBluetoothController
import android.content.Context
import com.myapplication.dataSource.bluetooth.IBluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	@Singleton
	fun provideBluetoothController(@ApplicationContext context: Context): IBluetoothController {
		return MusicalBluetoothController(context)
	}
}