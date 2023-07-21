package com.example.hiltdemo.startmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hiltdemo.R

class SingleTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = SingleTaskActivity::class.simpleName
        super.onCreate(savedInstanceState)
        Log.i(TAG,"onCreate()");
        setContentView(R.layout.activity_single_task)
    }
}