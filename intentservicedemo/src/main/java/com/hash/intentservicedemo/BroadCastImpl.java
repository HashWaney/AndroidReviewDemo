package com.hash.intentservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;

/**
 * Created by HashWaney on 2019/2/25.
 */

public class BroadCastImpl extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.logE("BroadCastImpl", "intent :" + intent);
        String action = intent.getAction();
        if (action.equals("com.hash")) {
            ToastUtil.showToast(context, action);
        }

    }
}
