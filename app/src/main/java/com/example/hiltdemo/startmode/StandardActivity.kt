package com.example.hiltdemo.startmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hiltdemo.R

class StandardActivity : AppCompatActivity() {
    val TAG = "StandardActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"onCreate()");
        setContentView(R.layout.activity_main2)
    }
}