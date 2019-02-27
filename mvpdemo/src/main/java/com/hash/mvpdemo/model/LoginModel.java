package com.hash.mvpdemo.model;

/**
 * Created by HashWaney on 2019/2/27.
 *
 *  Model的逻辑交互,通过传递过来用户交互产生的一些信息,将这些信息通过逻辑交互产生一些应答.
 */

public class LoginModel {

    public void login(String userName, ILoginResult iLoginResult) {
        if (userName.equals("zhangsan")) {
            if (iLoginResult != null) {
                iLoginResult.success(200, "登录成功");
            }
        } else {
            if (iLoginResult != null) {
                iLoginResult.error(403);
            }

        }

    }
}
