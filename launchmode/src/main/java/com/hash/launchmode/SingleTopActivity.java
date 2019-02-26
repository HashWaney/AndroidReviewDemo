package com.hash.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by HashWaney on 2019/2/24.
 */

public class SingleTopActivity extends AppCompatActivity {
    private String Tag = SingleTopActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletop);
        Log.e(Tag, "onCreate"+this.hashCode());
        findViewById(R.id.tvBack)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SingleTopActivity.this, MainActivity.class);

                        startActivity(intent);
                        //todo finish
//                        finish();
                    }
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(Tag, "onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(Tag, "onStart"+this.hashCode())
        ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(Tag,"onResume")
                ;
    }
}
