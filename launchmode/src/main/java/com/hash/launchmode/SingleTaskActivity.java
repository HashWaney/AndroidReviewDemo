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

public class SingleTaskActivity extends AppCompatActivity {

    private static final String Tag = SingleTaskActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletask);
        Log.e(Tag, "onCreate");
        findViewById(R.id.tvJump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTaskActivity.this, ThirdActivity.class);
                startActivity(intent);
                // TODO: 2019/2/24 加入这行代码之后,在启动这个Activity的时候,会重新创建这个实例 
//                finish();
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
        Log.e(Tag, "onStart");
    }
}
