package com.hash.mvpdemo.presenter;

import com.hash.mvpdemo.model.ILoginResult;
import com.hash.mvpdemo.model.LoginModel;
import com.hash.mvpdemo.view.ILoginView;

/**
 * Created by HashWaney on 2019/2/27.
 * 其作用使得View和Model进行交互.
 * View只用专注于用户的交互
 * Model只用专注于逻辑的处理
 */

public class LoginPresenter {

    private ILoginView loginView;
    private LoginModel loginModel;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    public void login(ILoginResult loginResult) {
        loginModel.login(loginView.getName(), loginResult);
    }


}
