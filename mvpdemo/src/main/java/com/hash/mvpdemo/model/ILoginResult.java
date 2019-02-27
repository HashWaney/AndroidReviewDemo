package com.hash.mvpdemo.model;

/**
 * Created by HashWaney on 2019/2/27.
 *
 * 将Model的逻辑交互处理的结果通过回调形式
 */

public interface ILoginResult {
    void error(int code);

    void success(int code, String msg);

}
