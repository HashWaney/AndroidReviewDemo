package com.hash.aidldemo;
import  com.hash.aidldemo.MessageBean;
//in : 客户端数据对象流向服务端，并且服务端对该数据对象的修改不会影响到客户端。
// out : 数据对象由服务端流向客户端，（客户端传递的数据对象此时服务端收到的对象内容为空，服务端可以对该数据对象修改，并传给客户端）
// inout : 以上两种数据流向的结合体。（但是不建议用此tag,会增加开销）

interface IDemandManager{
    MessageBean getDemand();

     void setDemandIn(in MessageBean msg);//客户端->服务端

     //out和inout都需要重写MessageBean的readFromParcel方法
     void setDemandOut(out MessageBean msg);//服务端->客户端

     void setDemandInOut(inout MessageBean msg);//客户端<->服务端
}