package com.hash.intentservicedemo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String _url = "http://file.yl1001.com/apk/job_btn_user.apk";
    private String Tag = MainActivity.class.getSimpleName();
    private TextView ivShowImg;
    private boolean isBinder = false;
    private BroadcastReceiver br = new BroadCastImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivShowImg = findViewById(R.id.showImg);
        //定义IntentService去下载Apk
        findViewById(R.id.click1)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, CustomIntentService.class);
                        intent.putExtra("download_url", _url)
                        ;
                        startService(intent);
                    }
                });

        //*************************以下是通过start的形式开启服务***********************************//
        // 停止服务
        findViewById(R.id.click3)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MyService.class);
                        stopService(intent)
                        ;


                    }
                });
        //开启服务
        findViewById(R.id.click2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MyService.class);

                        startService(intent);
                    }
                });

        //***************************以下是通过bind的形式开启服务*************************************************///
        final ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.logE(Tag, "onServiceConnected=------" + service);
                BindService.SimpleBinder simpleBinder = (BindService.SimpleBinder) service;
                int sum = 0;
                try {
                    sum = simpleBinder.add(2, 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isBinder = true;
                ToastUtil.showToast(MainActivity.this, "服务端处理结果返回---" + sum + " 服务绑定状态:" + isBinder);


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.logE(Tag, "onServiceDisconnected");
                isBinder = false;

            }
        };
        //绑定一个服务
        findViewById(R.id.click4)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, BindService.class);
                        bindService(intent, conn, BIND_AUTO_CREATE);

                    }
                });

        //解绑一个服务
        findViewById(R.id.click5)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.logE(Tag, "当前服务绑定状态:" + isBinder);
                        if (isBinder) {
                            unbindService(conn);
                            isBinder = !isBinder;
                        }

                    }
                });

        ///发送静态广播
        findViewById(R.id.click6)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, BroadCastReceiveSimple.class);
                                intent.putExtra("Component", "我是MainActivity传递过来的");
                                sendBroadcast(intent);
                            }
                        }
                );

        //发送动态广播
        findViewById(R.id.click7)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction("com.hash");
                        sendBroadcast(intent);
                    }
                });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ((Button) findViewById(R.id.click1)).setText("子线程更新Ui");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.logE(Tag, "当前线程:" + Thread.currentThread().getName());
                ((Button) findViewById(R.id.click1)).post(new Runnable() {
                    @Override
                    public void run() {
                        Log.logE(Tag, "当前线程:" + Thread.currentThread().getName());
                    }
                });

            }
        }).start();


        //使用HandleThread来实现异步替换显示数据并且将数据在主线程中更新
        HandlerThread handlerThread = new HandlerThread("updateData");
        handlerThread.start();
        //子线程的Looper 构造
        handler = new SunThreadHandler(handlerThread.getLooper());
        handler.sendMessage(handler.obtainMessage());


    }

    private SunThreadHandler handler;

    class SunThreadHandler extends Handler {
        public SunThreadHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.logE(Tag, "当前要处理的消息:" + msg);
            handleIntent();
        }
    }
    // Main的Looper 构造
    private Handler uiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Log.logE(Tag, "接收到子线程发送的消息: =" + msg + " ====== " + Thread.currentThread().getName());
            switch (msg.what) {
                case 1000:
                    Bundle data = msg.getData();
                    String message = data.getString("Message");
                    int count = data.getInt("Count");
                    ivShowImg.setText(message + "----" + count);
                    break;
            }

        }
    };
    private int count;

    private void handleIntent() {
        //模拟子线程耗时操作
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            //定义主线程Handler
            Message message = uiHandler.obtainMessage();
            message.what = 1000;
            Bundle bundle = new Bundle();
            bundle.putInt("Count", count);
            bundle.putString("Message", "Hash");
            message.setData(bundle);
            Log.logE(Tag, "主线程获取的消息::" + message+ "-----" + Thread.currentThread().getName());
            uiHandler.sendMessage(message);
            if (count > 15)
                break;

        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        //动态注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.hash");
        registerReceiver(br, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //动态注销广播
        unregisterReceiver(br);
    }
}
