package com.example.hiltdemo.startmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hiltdemo.R

class SingleInstanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_instance)
    }
}