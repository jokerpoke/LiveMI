package com.example.xgj.livemi.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by chen on 2017/4/17.
 */

public class MyApp extends Application {
    public static MyApp mMyApp;

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mMyApp = this;
        mContext=this;
    }

    public static MyApp getmMyApp() {
        return mMyApp;
    }


}
