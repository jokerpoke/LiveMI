package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.content.Intent;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseActivity;

public class ContactusActivity extends BaseActivity {

    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_contactus;
    }

    @Override
    protected String initTitle() {
        return "客服中心";
    }

    @Override
    protected void onActivityPrepared() {

    }

    @Override
    protected void initIntentData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, ContactusActivity.class);
        context.startActivity(intent);
    }
}
