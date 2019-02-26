// IServiceCallback.aidl
package com.hash.aidldemo.aidlservice;

// Declare any non-default types here with import statements

interface IServiceCallback {

   //将计算结果回调出去
   void onCallBackSum(int size);
   //将吃东西结果回调出去
   void onCallBackEat(String text);

}
