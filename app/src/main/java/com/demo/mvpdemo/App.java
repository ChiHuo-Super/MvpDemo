package com.demo.mvpdemo;

import android.app.Application;

import com.demo.mvpdemo.exception.CrashHandler;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
    }
}
