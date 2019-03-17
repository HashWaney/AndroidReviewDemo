package com.hash.memoryleakdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HashWaney on 2019/2/28.
 * 监听器未释放 引用当前Act 导致Act 无法被GC回收
 */

public class ListenerRegisterLeakAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActManager.getInstance().addListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解决办法
        ActManager.getInstance().removeListener(this);

    }
}
