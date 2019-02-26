package com.hash.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by HashWaney on 2019/2/25.
 */

public class MessageService extends Service {

    private String Tag = MessageService.class.getSimpleName();

    private Messenger messeg = new Messenger(new MessengerHandler());

    class MessengerHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            Log.e(Tag, "dispatchMessage" + msg);
            switch (msg.what) {
                case 1000:
                    Bundle data = msg.getData();
                    Toast.makeText(MessageService.this, "跨应用的服务端接收到了来自客户端的消息::"+data.getString("MainActivity"), 0).show();
                    break;
            }

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Tag, "onCreate")
        ;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(Tag, "onBind")
        ;
        return messeg.getBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(Tag, "onDestroy")
        ;
    }
}
