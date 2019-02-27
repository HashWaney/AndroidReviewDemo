package com.hash.intentservicedemo;

/**
 * Created by HashWaney on 2019/2/25.
 */

public class SingleTop {
    //饿汉式实现单例
    static class SingleTop1 {
        //初始化一个静态的局部变量
        private static SingleTop1 singleTop1 = new SingleTop1();

        private SingleTop1() {
        }

        public static SingleTop1 getSingleTop1() {
            return singleTop1;
        }

    }

    //懒汉式实现单例模式
    static class SingleTop2 {
        private static SingleTop2 singleTop2;

        private SingleTop2() {
        }

        //双重加锁方式实现线程安全
        public  static SingleTop2 getSingleTop2() {
            if (singleTop2 == null) {
                synchronized (SingleTop2.class) {
                    if (singleTop2 == null)
                        singleTop2 = new SingleTop2();
                }
            }
            return singleTop2;


        }

    }
}
