package com.hash.memoryleakdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by HashWaney on 2019/2/28.
 */

public class ActManager {

    private static ActManager instance;

    private static ArrayList<AppCompatActivity> mData = new ArrayList<>();

    private ActManager() {
        

    }

    public static ActManager getInstance() {
        if (instance == null) {
            synchronized (ActManager.class) {
                if (instance == null) {
                    instance = new ActManager();
                }
            }
        }
        return instance;
    }

    public static void addListener(AppCompatActivity appCompatActivity) {
        mData.clear();
        if (appCompatActivity != null)
            mData.add(appCompatActivity);
    }

    public static void removeListener(AppCompatActivity appCompatActivity) {
        if (!mData.isEmpty())
            mData.remove(appCompatActivity);
    }


}
