package com.hash.intentservicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by HashWaney on 2019/2/24.
 * 播放音乐
 */

public class MyService extends Service {

    private String Tag = MyService.class.getSimpleName();

    private MediaPlayer mediaPlayer = null;

    private boolean isReady = false;

    @Override
    public void onDestroy() {
        Log.e(Tag, "onDestroy")
        ;
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();

            //释放媒体资源
            mediaPlayer.release();
            Toast.makeText(this, "停止背景音乐播放",
                    0).show();


            mediaPlayer = null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Tag, "onCreate");
        //初始化媒体播放器
        mediaPlayer = MediaPlayer.create(this, R.raw.xiaoxingyun);
        if (mediaPlayer == null)
            return;
        mediaPlayer.stop();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.release();
                stopSelf();
                return false;
            }
        });
        try {
            mediaPlayer.prepare();
            isReady = true;
        } catch (Exception e) {
            e.printStackTrace();
            isReady = false;
        }
        if (isReady)
            //将背景音乐置为循环播放
            mediaPlayer.setLooping(isReady);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(Tag, "onStartCommand");
        if (isReady && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Toast.makeText(this, "开始播放背景音乐", 0)
                    .show();
        }
        return START_STICKY;


    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(Tag, "onBind")
        ;
        return null;
    }


}
