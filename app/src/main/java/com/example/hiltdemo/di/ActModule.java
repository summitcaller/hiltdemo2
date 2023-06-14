package com.example.hiltdemo.di;

import java.util.Calendar;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * author:aclidae on 2023/6/6 13 42
 * des:
 * @author aclidae
 */

@Module
@InstallIn({ActivityComponent.class})
public class ActModule{

//    @Provides
//    Calendar provideCa(){
//        return Calendar.getInstance();
//    }

}
