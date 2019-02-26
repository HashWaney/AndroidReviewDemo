package com.hash.intentservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by HashWaney on 2019/2/25.
 */

public class BindService extends Service {


    private String Tag = BindService.class.getSimpleName();
    private SimpleBinder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        com.hash.intentservicedemo.Log.logE(Tag, "onCreate");
        binder = new SimpleBinder();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        com.hash.intentservicedemo.Log.logE(Tag, "onBind");
        return binder;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        com.hash.intentservicedemo.Log.logE(Tag, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public boolean onUnbind(Intent intent) {
        com.hash.intentservicedemo.Log.logE(Tag, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        com.hash.intentservicedemo.Log.logE(Tag, "onDestroy");

    }

    /**
     *
     */
    class SimpleBinder extends Binder {
        public int add(int a, int b) throws InterruptedException {
            Thread.sleep(20000);
            return a * 2 + b;
        }

    }
}
