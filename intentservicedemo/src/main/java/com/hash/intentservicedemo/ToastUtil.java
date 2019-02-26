package com.hash.intentservicedemo;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by HashWaney on 2019/2/25.
 */

public class ToastUtil {
    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG)
                .show();
    }
}
