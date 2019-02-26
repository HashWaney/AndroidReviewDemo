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

public class ThirdActivity extends AppCompatActivity {
    private String Tag = ThirdActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        findViewById(R.id.toNext)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        Log.e(Tag,"onCreate")
                ;

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(Tag,"onStart");
    }
}
