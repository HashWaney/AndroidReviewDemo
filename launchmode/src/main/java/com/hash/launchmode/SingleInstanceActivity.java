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

public class SingleInstanceActivity extends AppCompatActivity {
    private String Tag = SingleInstanceActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleinstance);
        Log.e(Tag, "onCreate")
        ;
        findViewById(R.id.toNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleInstanceActivity.this, ThirdActivity.class);
                startActivity(intent);

            }
        });
    }
}
