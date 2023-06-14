package com.example.hiltdemo.di

import javax.inject.Inject

/**
 * author:aclidae on 2023/6/5 13 49
 * des: 学生
 */
class Student {
    fun getTime():Long{
        return System.currentTimeMillis()
    }
}