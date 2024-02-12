package com.myapplication

import android.app.Application
import com.myapplication.dataSource.bluetooth.MusicalBluetoothService
import com.myapplication.dataSource.database.MusicalDatabase
import com.myapplication.repository.users.UserMusicalManager

class MusicalInitApp : Application() {
    override fun onCreate() {
        super.onCreate()

        MusicalDatabase.initDatabase(applicationContext)
        UserMusicalManager.initUserManager()
        MusicalBluetoothService.initBluetoothService(applicationContext)
    }
}