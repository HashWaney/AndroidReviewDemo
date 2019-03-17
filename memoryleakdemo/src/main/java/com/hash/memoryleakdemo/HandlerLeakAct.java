package com.hash.memoryleakdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by HashWaney on 2019/2/28.
 * 非静态内部类持有外部类引用导致内存泄露
 */

public class HandlerLeakAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);

    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //处理消息
            Log.e(HandlerLeakAct.class.getSimpleName()
                    , "handler:" + msg.what);
        }
    };


    public void finishAct(View view) {

        //结束当前act
        finish();
        //创建一个子线程,通过handler与主线程进行通信
        createThreadCommunitToUi();
        //修复Handler引起的内存泄露
        fixedHandlerLeak();

    }

    private void fixedHandlerLeak() {


    }

    private static class MyHandler extends Handler {
        private WeakReference<HandlerLeakAct> handlerLeakActWeakReference;

        public MyHandler(HandlerLeakAct act) {
            handlerLeakActWeakReference = new WeakReference<HandlerLeakAct>(act);
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (handlerLeakActWeakReference.get() != null) {
                HandlerLeakAct handlerLeakAct = handlerLeakActWeakReference.get();
            }
        }
    }

    private void createThreadCommunitToUi() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(100);
                        try {
                            Thread.sleep(8000);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

        ).start();


    }

}
