package com.hash.service.mm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/**
 * Created by HashWaney on 2019/2/24.
 */

public class CommunService extends Service {
    private static final String Tag = CommunService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Tag, "onCreate");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(Tag, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(Tag, "onDestroy");
    }

    ICommunCallBack iCommunCallBack;

    ICommun.Stub binder = new ICommun.Stub() {
        @Override
        public void sum(final int a, final int b) throws RemoteException {
            Log.e(Tag, "客户端触发了sum方法------>  " + Thread.currentThread().getName());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int x = a * 2 + b;
                    if (iCommunCallBack != null)
                        try {
                            Log.e(Tag, "当前线程:" + Thread.currentThread().getName());
                            iCommunCallBack.onSumCallBack(x);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                }

            }).start();
            int x = a * 2 + b;
            if (iCommunCallBack != null)
                try {
                    Log.e(Tag, "当前线程:" + Thread.currentThread().getName());
                    iCommunCallBack.onSumCallBack(x);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
        }

        @Override
        public void display(boolean content) throws RemoteException {
            if (content) {
                Log.e(Tag, "客户端触发了该方法了+  display-------->" + Thread.currentThread().getName());
                if (iCommunCallBack != null)
                    iCommunCallBack.onDisplayCallBack("我是服务端返回的数据!!!!");

            }

        }

        @Override
        public void registCallBack(ICommunCallBack callBack) throws RemoteException {
            Log.e(Tag, "客户端触发了注册方法");
            if (callBack != null)
                iCommunCallBack = callBack;


        }

        @Override
        public void unRegistCallBack(ICommunCallBack callBack) throws RemoteException {
            Log.e(Tag, "客户端出发了解绑方法")
            ;
            if (callBack != null)
                iCommunCallBack = null;

        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(Tag, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(Tag, "onBind");
        return binder;
    }
}
