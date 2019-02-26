package com.hash.intentservicedemo;

/**
 * Created by HashWaney on 2019/2/25.
 */

public class SingleTop {

    private static SingleTop sInstance;

    //懒汉式 加入了同步关键字修饰静态方法
    public synchronized static SingleTop getInstance() {
        if (sInstance == null)
            sInstance = new SingleTop();
        return sInstance;
    }
    //饿汉式

    private static SingleTop sInstance2;

    public static SingleTop getsInstance2() {
        sInstance2 = new SingleTop();
        return sInstance2;

    }


}
