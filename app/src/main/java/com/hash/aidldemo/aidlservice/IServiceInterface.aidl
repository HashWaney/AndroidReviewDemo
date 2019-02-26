// IServiceInterface.aidl
package com.hash.aidldemo.aidlservice;

import com.hash.aidldemo.aidlservice.IServiceCallback;
// Declare any non-default types here with import statements

interface IServiceInterface {

    // 计算
    void sum(int a,int b);
    //吃东西
    void eat(boolean isEat);
    //注册
    void registerCallback(IServiceCallback i);
    //解绑
    void unregisterCallback(IServiceCallback o);

}
