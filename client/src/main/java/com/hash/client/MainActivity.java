package com.hash.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hash.service.mm.ICommun;
import com.hash.service.mm.ICommunCallBack;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = MainActivity.class.getSimpleName();
    ICommun iCommun = null;
    boolean isBind;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(Tag, "onServiceConnected()");
            iCommun = ICommun.Stub.asInterface(service);
            Log.e(Tag, "获取AIDL通信接口:" + iCommun);
            //注册接口一个接口回调服务端处理好的数据给客户端
            try {
                iCommun.registCallBack(new ICommonCallBackImpl());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            isBind = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(Tag, "onServiceDisConnected");
            try {
                iCommun.unRegistCallBack(new ICommonCallBackImpl());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            conn = null;
            isBind = false;


        }
    };

    class ICommonCallBackImpl extends ICommunCallBack.Stub {

        @Override
        public void onSumCallBack(int size) throws RemoteException {
            Toast.makeText(MainActivity.this, "服务端计算结果:---->" + size, 0)
                    .show();

        }

        @Override
        public void onDisplayCallBack(String content) throws RemoteException {
            Toast.makeText(MainActivity.this, "服务端返回信息---->" + content, 0)
                    .show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServices();
        findViewById(R.id.stop)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isBind) {
                            isBind = !isBind;
                            unbindService(conn);
                        }
                    }
                });
        findViewById(R.id.start)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //如果没有绑定
                        if (!isBind)
                            startServices();
                    }
                });
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBind) {
                    try {
                        iCommun.display(true);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "服务未连接", 0).show();
                }

            }
        });
        findViewById(R.id.calc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBind)
                    try {
                        iCommun.sum(2, 5);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                else
                    Toast.makeText(MainActivity.this, "服务未连接", 0).show();
            }
        });


    }

    private void startServices() {
        Intent intent = new Intent("mm.hash");
        intent.setPackage("com.hash.service");
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
