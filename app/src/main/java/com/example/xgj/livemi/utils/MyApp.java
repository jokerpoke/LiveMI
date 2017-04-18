package com.example.xgj.livemi.utils;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by chen on 2017/4/17.
 */

public class MyApp extends Application {
    private MyApp mMyApp;
    private Toast toast;
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mMyApp = this;
        mContext=this;
    }

    public MyApp getmMyApp() {
        return mMyApp;
    }

    public  void showBKToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
