package com.myapplication.dataSource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myapplication.dataSource.database.dao.MusicalUsersDAO
import com.myapplication.model.users.MusicalUsers

@Database(entities = [MusicalUsers::class], version = 1)
abstract class MusicalDatabase: RoomDatabase(){
    abstract fun musicalUsersDAO() : MusicalUsersDAO

    companion object{
        private lateinit var instance: MusicalDatabase
        fun initDatabase(context: Context){
            instance = Room.databaseBuilder(context, MusicalDatabase::class.java, "Musical-database")
                .fallbackToDestructiveMigration().build()
        }

        fun getInstance() : MusicalDatabase{
            return instance
        }
    }
}