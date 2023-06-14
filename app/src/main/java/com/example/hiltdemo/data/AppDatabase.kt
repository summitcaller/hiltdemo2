package com.example.hiltdemo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hiltdemo.data.dao.UserDao
import com.example.hiltdemo.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
