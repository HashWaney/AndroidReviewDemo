package com.hash.aidldemo;

import android.os.AsyncTask;

/**
 * Created by HashWaney on 2019/2/25.
 */

public class ChildClass extends FatherClass {

    private static int code;


    private ChildClass() {

    }

    private static ChildClass sInstance;

    //同步关键字修饰静态方法
    public static synchronized ChildClass getsInstance() {
        if (sInstance == null) {
            //类锁
            synchronized (ChildClass.class) {
                if (sInstance == null) {
                    sInstance = new ChildClass();
                }
            }
        }
        return sInstance;
    }


    private void setCode() {
        //同步锁---锁对象
        //防止其他线程访问同一个对象中的synchronized代码块或函数
        synchronized (this) {
            //....代码


        }

    }

    //同步锁静态方法--->锁了class对象
    private synchronized static void setName() {


    }
    //同步锁方法-->锁对象
    private synchronized void setApi() {

    }

    //同步锁class对象
    private void setNumber() {
        synchronized (ChildClass.class) {
        }
    }


}
