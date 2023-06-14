package com.example.hiltdemo.di

import com.example.hiltdemo.core.Core
import com.example.hiltdemo.core.CoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

/**
 * author:aclidae on 2023/6/7 11 48
 * des:
 */

@Module
@InstallIn(ActivityComponent::class)
interface CoreRes {

    @Binds
    fun bindCore(coreImpl: CoreImpl):Core
}