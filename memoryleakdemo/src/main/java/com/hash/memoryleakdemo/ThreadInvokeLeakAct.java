package com.hash.memoryleakdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by HashWaney on 2019/2/28.
 */

public class ThreadInvokeLeakAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);

    }

    public void finishAct(View view) {
        finish();
        //错误案例
//        memoryLeak();
        //修正案例
        modifyMemoryLeak();

    }

    //修正后的案例
    private void modifyMemoryLeak() {
        new Thread(new MyRunnable()).start();
    }

    //内存泄露的案例
    //分析: finish()销毁,但是该Act还在线程中运行,匿名内部类Runnable对象持有了Act的引用,导致了Act不能被GC回收
    private void memoryLeak() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //模拟耗时操作
                    Thread.sleep(13000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(13000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
