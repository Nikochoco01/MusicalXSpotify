package com.myapplication

import android.app.Application
import com.myapplication.dataSource.database.MusicalDatabase

class MusicalInitApp : Application() {
    override fun onCreate() {
        super.onCreate()

        MusicalDatabase.initDatabase(applicationContext)
    }
}