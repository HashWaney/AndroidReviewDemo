package com.hash.launchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String Tag =MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.toNext)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, SingleTopActivity.class);
//                        Intent intent = new Intent(MainActivity.this, SingleInstanceActivity.class);
//                        Intent intent = new Intent(MainActivity.this, SingleTaskActivity.class);
                        startActivity(intent);
                    }
                });
        Log.e(Tag,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(Tag,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(Tag,"onRestart")
                ;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(Tag,"onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(Tag,"onPause")
                ;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(Tag,"onStop")
                ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(Tag,"onDestroy")
                ;
    }
}
