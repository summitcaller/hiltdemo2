package com.example.hiltdemo.di

import android.content.Context
import androidx.room.Room
import com.example.hiltdemo.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * author:aclidae on 2023/6/6 19 54
 * des:
 */

@Module
@InstallIn(SingletonComponent::class)
object SingleDBModule {

    @Provides
    @Singleton
    fun provideUserDao(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}