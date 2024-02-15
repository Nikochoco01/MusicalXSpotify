package com.myapplication

import android.app.Application
import com.myapplication.dataSource.database.MusicalDatabase
import com.myapplication.repository.users.UserMusicalManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MusicalInitApp : Application() {
    override fun onCreate() {
        super.onCreate()

        MusicalDatabase.initDatabase(applicationContext)
        UserMusicalManager.initUserManager()
    }
}