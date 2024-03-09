package com.myapplication

import android.app.Application
import com.myapplication.dataSource.database.MusicalDatabase
import com.myapplication.dataSource.phoneFile.PhoneFilesDataSource
import com.myapplication.dataSource.phoneFile.PhoneReader
import com.myapplication.repository.users.UserMusicalManager

class MusicalInitApp : Application() {
    override fun onCreate() {
        super.onCreate()

        MusicalDatabase.initDatabase(applicationContext)
        PhoneReader.initPhoneReader()
        UserMusicalManager.initUserManager()
    }
}