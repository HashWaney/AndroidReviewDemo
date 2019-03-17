package com.hash.memoryleakdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by HashWaney on 2019/2/28.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
