package com.example.xgj.livemi.utils;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by chen on 2017/4/17.
 */

public class MyApp extends Application {
    public static MyApp mMyApp;
    private RefWatcher _refWatcher;

    private Context mContext;


    private Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mMyApp = this;
        mContext = this;
        initGson();
//        LeakCanary.install(this);
    }

    private void initGson() {
        if (gson == null) {
            gson = new Gson();
        }
    }


    public Gson getGson() {
        return gson;
    }

    public static RefWatcher getRefWatcher() {
        return MyApp.getmMyApp()._refWatcher;
    }

    public static MyApp getmMyApp() {
        return mMyApp;
    }


}
