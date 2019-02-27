package com.hash.mvpdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hash.mvpdemo.R;
import com.hash.mvpdemo.model.ILoginResult;
import com.hash.mvpdemo.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ILoginView, ILoginResult {
    private EditText etName;
    LoginPresenter loginPresenter;

    private String Tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.check).setOnClickListener(this);
        etName = (EditText) findViewById(R.id.etName);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        loginPresenter.login(this);


    }

    @Override
    public String getName() {
        return TextUtils.isEmpty(etName.getText().toString()) ? "" : etName.getText().toString().trim();
    }

    @Override
    public void error(int code) {
        Log.e(Tag, "当前登录状态码为:" + code);
        Toast.makeText(this, "当前登录状态码为:" + code, Toast.LENGTH_LONG)
                .show();

    }

    @Override
    public void success(int code, String msg) {
        Toast.makeText(this, "当前登录状态码为:" + code, Toast.LENGTH_LONG
        ).show();
    }
}
