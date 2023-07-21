package com.example.hiltdemo.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.example.hiltdemo.startmode.SingleInstanceActivity
import com.example.hiltdemo.startmode.SingleTaskActivity
import com.example.hiltdemo.startmode.StandardActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Appendable
import javax.inject.Inject

/**
 * author:aclidae on 2023/7/10 13 48
 * des: 用于测试activity的启动模式
 */

@HiltViewModel
class ModeViewModel @Inject constructor(private val application: Application): AndroidViewModel(application){
    val TAG = ModeViewModel::class.simpleName
    var context:Context? = null
    fun startStandard(){
        if (context != null) {
            val intent = Intent(context,StandardActivity::class.java)
            //连续启动三次
            Log.i(TAG,"启动1")
            context!!.startActivity(intent)
            Log.i(TAG,"启动2")
            context!!.startActivity(intent)
            Log.i(TAG,"启动3")
            context!!.startActivity(intent)
        }
    }

    fun startSingleTask(){
        val intent = Intent(application,SingleTaskActivity::class.java)
        //连续启动三次
        Log.i(TAG,"startSingleTask 启动1")
        application.startActivity(intent)
        Log.i(TAG,"startSingleTask 启动2")
        application.startActivity(intent)
        Log.i(TAG,"startSingleTask 启动3")
        application.startActivity(intent)
    }


    fun startSingleInstance(){
        val intent = Intent(application,SingleInstanceActivity::class.java)
        //连续启动三次
        application.startActivity(intent)
        application.startActivity(intent)
        application.startActivity(intent)
    }


    fun startSingleTop(){
        val intent = Intent(application,SingleInstanceActivity::class.java)
        //连续启动三次
        application.startActivity(intent)
        application.startActivity(intent)
        application.startActivity(intent)
    }
}