package com.example.hiltdemo.di

import android.content.Context
import androidx.room.Room
import com.example.hiltdemo.data.AppDatabase
import com.example.hiltdemo.data.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author:aclidae on 2023/6/9 11 38
 * des:
 */


@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideUserDao(@ApplicationContext context: Context): UserDao {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build().userDao()
    }
}