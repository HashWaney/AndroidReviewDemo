package com.hash.aidldemo.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by HashWaney on 2019/2/24.
 */

    public class AidlService extends Service {

    public  static  final  String Tag =AidlService.class.getClass().getSimpleName();
    private IServiceCallback callback;

    private IServiceInterface.Stub binder = new IServiceInterface.Stub() {
        @Override
        public void sum(int a, int b) throws RemoteException {
            Log.e(Tag,"sum 执行了");
            //计算
            int x = a * 2 + b;
            if (callback != null) {
                callback.onCallBackSum(x);
            }

        }

        @Override
        public void eat(boolean isEat) throws RemoteException {
            Log.e(Tag,"执行了eat");
            if (isEat) {
                if (callback != null) {
                    callback.onCallBackEat("我想吃菠萝");
                }
            }

        }

        @Override
        public void registerCallback(IServiceCallback i) throws RemoteException {
            Log.e(Tag,"执行了注册");
            if (i != null)
                callback = i;

        }

        @Override
        public void unregisterCallback(IServiceCallback o) throws RemoteException {
            Log.e(Tag,"执行了解绑操作");
            if (o != null)
                callback = null;

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Tag,"onCreate");;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(Tag,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
