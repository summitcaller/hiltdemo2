package com.example.hiltdemo.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Scheduler.Worker
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Calendar

/**
 * author:aclidae on 2023/6/5 13 46
 * des:
 */

@Module
@InstallIn(ActivityComponent::class)// 注入到Activity里面去
object TestModule {

    @Provides
    @ActivityScoped
    fun getStudent(): Student {
        Log.i("LHNNB","getStudent()")
        return Student()
    }

    @Provides
    fun getCa():Calendar{
        return Calendar.getInstance()
    }

    @Provides
    fun provideWorker():Worker{
        Log.i("lhnnb","provideWorker()")
        return Schedulers.io().createWorker()
    }
}