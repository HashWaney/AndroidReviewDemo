package com.hash.service;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = findViewById(R.id.click);

        HandlerThread thread = new HandlerThread("Thread");
        thread.start();

        new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

            }
        };

    }
}
