package com.hash.intentservicedemo;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by HashWaney on 2019/2/24.
 * IntentService 和 Service的区别:
 * IntentService是Service的一个子类,其内部维护了一个HandleThread 那么这个HandlerThread是继承Thread的
 * 在IntentService的onCreate方法就开启了线程,说明IntentService是 可以进行一些耗时操作.
 * 但是Service是依附在主线程,如果进行耗时操作,可能就会
 */

public class CustomIntentService extends IntentService {

    private static final String Tag = CustomIntentService.class.getSimpleName();

    private int fileLength, downloadLength;
    private Handler handler = new Handler();
    private NotificationCompat.Builder builder;
    private NotificationManager manager;
    private int _notificationID = 1024;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public CustomIntentService() {
        super("CustomIntentService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Tag, "onCreate")
        ;
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e(Tag, "onStartCommand;;;;" + intent)
        ;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(Tag, "onDestroy")
        ;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(Tag, "onBind")
        ;
        return super.onBind(intent);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(Tag, "onUnbind")
        ;
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(Tag, "onRebind")
        ;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(Tag, "onHandleIntent:====" + intent);
        try {
            Bundle extras = intent.getExtras();
            String downloadUrl = extras.getString("download_url");
            File dirs = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download");//文件保存地址
            if (!dirs.exists()) {
                dirs.mkdir();
            }

            File file = new File(dirs, "yilan.apk");

            Log.e(Tag, "下载启动了:" + downloadUrl + " to " + file.getAbsolutePath());
//            manager.notify(_notificationID, builder.build());
            //开始下载

            downloadFile(downloadUrl, file);
            Thread.sleep(1999);
            //下载结束
//            builder.setProgress(0, 0, false)
//            ;//移除进度条
//            builder.setContentText("下载结束")
//            ;
//            manager.notify(
//                    _notificationID, builder.build()
//            );


        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(Tag, "下载结束")
        ;


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void downloadFile(String downloadUrl, File file) {
        Log.e(Tag,"downloadUrl:"+downloadUrl+" , file:"+file)
                ;
        FileOutputStream _outputStream=null;//文件输出流
        InputStream _inputStream = null;
        try {
            _outputStream = new FileOutputStream(file);
            sendNotification("文件开始下载", "APk开始下载");
            URL url = new URL(downloadUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            fileLength = Integer.valueOf(httpURLConnection.getHeaderField("Content-Length"));
            _inputStream = httpURLConnection.getInputStream();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                //更新下载进度
                ;
                byte[] bytes = new byte[1024 * 8];
                int len;
                while ((len = _inputStream.read(bytes)) != -1) {
                    _outputStream.write(bytes, 0, len);
                    downloadLength += len;
                    //通知下载的进度
                    double progress = Math.floor(1000.0 * downloadLength / fileLength) / 10;
                    sendNotification("Apk开始下载", "下载进度：" + progress + "%");
                }

                //5. 文件下载完成
                _outputStream.close();
                cancelNotification(); //重新出现滚动消息
                sendNotification("APk下载完成", "APK下载完毕");
            } else {
                Log.e(Tag, "responseCode error:" + responseCode)
                ;
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(Tag,"error "+e.getMessage())
                    ;
        } finally {
            try {
                if (_outputStream
                        != null)
                    _outputStream.close();
                if (_inputStream != null)
                    _inputStream.close();
            } catch (Exception e
                    ) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 取消通知
     */
    public void cancelNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(_notificationID);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotification(String text, String
            ticker) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("音乐下载")
                .setContentText(text)
                .setTicker(ticker);
        Notification n = builder.build();
        manager.notify(_notificationID, n);


    }

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            int _progress = (int) (downloadLength * 100 / fileLength);
            builder.setContentTitle("下载中....." + _progress + "%")
            ;
            builder.setProgress(100, _progress, false)
            ;
            manager.notify(_notificationID, builder.build());
            handler.postDelayed(run, 1000)
            ;

        }
    };
}
