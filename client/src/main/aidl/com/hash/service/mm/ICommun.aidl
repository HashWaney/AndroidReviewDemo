package com.hash.service.mm;

import com.hash.service.mm.ICommunCallBack;
interface ICommun{
    void sum(int a, int b);
    void display(boolean isDisplay);
    void registCallBack(ICommunCallBack callBack);
    void unRegistCallBack(ICommunCallBack callBack);

}