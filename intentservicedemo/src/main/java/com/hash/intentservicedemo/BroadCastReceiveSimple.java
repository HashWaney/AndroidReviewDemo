package com.hash.intentservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by HashWaney on 2019/2/25.
 */

public class BroadCastReceiveSimple extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            abortBroadcast();
//            Thread.sleep(10000);
            Bundle extras = intent.getExtras();
            String component = extras.getString("Component");
            ToastUtil.showToast(context, component);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
