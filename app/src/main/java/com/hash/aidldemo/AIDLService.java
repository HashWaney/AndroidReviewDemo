package com.hash.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by HashWaney on 2019/2/23.
 */

public class AIDLService extends Service {

    String Tag = AIDLService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return in;
    }


    //Stub内部继承Binder 具备跨进程传输能力
    IDemandManager.Stub in = new IDemandManager.Stub() {

        @Override
        public MessageBean getDemand() throws RemoteException {
            return new MessageBean("我是服务端", 1);
        }

        @Override
        public void setDemandIn(MessageBean msg) throws RemoteException {
            Log.e(Tag, "我是客户端:" + msg.toString());
            //客户端发送消息给服务端

        }

        @Override
        public void setDemandOut(MessageBean msg) throws RemoteException {
            //msg 为什么一定为空
            Log.e(Tag, "我是服务端:" + msg.toString());

            msg.setContent("我是服务端,我给你下达了命令");
            msg.setLevel(3);

        }

        @Override
        public void setDemandInOut(MessageBean msg) throws RemoteException {
            Log.e(Tag,"setDemandInOut ---我是客户端:"+msg.toString());
            msg.setContent("我是服务端,客户端可以向我发出请求");
            msg.setLevel(5);

        }
    };


}
