package com.hash.mvcdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText etName;
    private EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //视图层
        etName = (EditText) findViewById(R.id.inputName);
        etPwd = (EditText) findViewById(R.id.inputPwd);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //逻辑交互层 校验密码和用户名
                login();

            }
        });


    }

    //登录逻辑判断
    private void login() {
        if (checkValue()) {
            Toast.makeText(this, "登录成功,未做校验....", Toast.LENGTH_LONG)
                    .show();
        } else {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_LONG)
                    .show();
        }

    }

    private boolean checkValue() {
        if (!TextUtils.isEmpty(etName.getText().toString().trim()) && !TextUtils.isEmpty(etPwd.getText().toString().trim())) {
            return true;
        }
        return false;
    }
}
