package com.example.hiltdemo.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hiltdemo.di.Student;

import java.util.Calendar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * author:aclidae on 2023/6/5 14 30
 * des:
 * @author aclidae
 */
@AndroidEntryPoint
public class TestActivity extends AppCompatActivity {

    @Inject
    Student student;

    @Inject
    Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LHNNB","st " + student.getTime()+",,Ca =" + calendar.getTime());
    }
}
