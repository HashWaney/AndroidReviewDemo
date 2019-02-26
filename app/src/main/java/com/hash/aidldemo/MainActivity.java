package com.hash.aidldemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hash.aidldemo.aidlservice.IServiceCallback;
import com.hash.aidldemo.aidlservice.IServiceInterface;

public class MainActivity extends AppCompatActivity {
    ServiceConnection conn = null;
    IServiceInterface iServiceInterface = null;
    private boolean isBind = false;
    private String Tag = MainActivity.this.getClass().getSimpleName();
    ServiceConnection messengerConn = null;

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
        findViewById(R.id.eat)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            iServiceInterface.eat(true);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }
                });
        findViewById(R.id.calc)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            iServiceInterface.sum(1, 4);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }
                });

        findViewById(R.id.startProcess)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startProcess()
                                ;
                    }
                });
        findViewById(R.id.startRemoteProcess)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startRemoteProcess();

                    }
                });

    }
    //启动一个跨应用的服务进程
    private void startRemoteProcess() {
        Intent intent =new Intent("mm.hash.messenger");
        intent.setPackage("com.hash.service");

        messengerConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(Tag, "onServiceConnected ::"+Thread.currentThread().getName());
                Messenger messenger = new Messenger(service);
                //创建一条消息
                Message msgToServer = Message.obtain();
                //消息类型
                msgToServer.what=1000;
                Bundle bundle = new Bundle();
                bundle.putString(Tag, "我是来自于Main");
                msgToServer.setData(bundle);
                //发送一条消息给服务端
                try {
                    messenger.send(msgToServer);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                isBind = true;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBind = false;

            }
        };
        bindService(intent, messengerConn, BIND_AUTO_CREATE);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    //启动本应用的一个服务进程
    private void startProcess() {
        Intent intent = new Intent(this, MessageService.class);
        messengerConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(Tag, "onServiceConnected");
                Messenger messenger = new Messenger(service);
                //创建一条消息
                Message msgToServer = Message.obtain();
                //消息类型
                msgToServer.what=1000;
                Bundle bundle = new Bundle();
                bundle.putString(Tag, "我是来自于Main");
                msgToServer.setData(bundle);
                //发送一条消息给服务端
                try {
                    messenger.send(msgToServer);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                isBind = true;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBind = false;

            }
        };
        bindService(intent, messengerConn, BIND_AUTO_CREATE);
    }


    private void initService() {
        Intent intent = new Intent("com.leo.aidlTest");
        intent.setPackage("com.hash.aidldemo");
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(MainActivity.class.getSimpleName(), "onServiceConnected");
                //获取到AIDL对象
                iServiceInterface = IServiceInterface.Stub.asInterface(service);
                //注册监听器
                try {
                    iServiceInterface.registerCallback(new IServiceCallbackImpl());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    class IServiceCallbackImpl extends IServiceCallback.Stub {

        @Override
        public void onCallBackSum(int size) throws RemoteException {
            Toast.makeText(MainActivity.this, "计算结果 " + size, Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCallBackEat(String text) throws RemoteException {
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(messengerConn);
    }
}
