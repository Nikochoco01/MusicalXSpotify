package com.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapplication.database.dao.MusicalUsersDAO
import com.myapplication.model.users.MusicalUsers

@Database(entities = [MusicalUsers::class], version = 1)
abstract class MusicalDatabase: RoomDatabase(){
    abstract fun musicalUsersDAO() : MusicalUsersDAO
}