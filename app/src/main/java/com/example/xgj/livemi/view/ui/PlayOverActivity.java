package com.example.xgj.livemi.view.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PlayOverActivity extends BaseActivity {


    @BindView(R.id.btn_attention)
    Button btnAttention;
    @BindView(R.id.btn_close)
    Button btnClose;

    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_prepare;
    }

    @Override
    protected String initTitle() {
        return null;
    }

    @Override
    protected void onActivityPrepared() {

    }

    @Override
    protected void initIntentData() {

    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        //改变状态栏颜色
        Window window = getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(Color.parseColor("#000000"));
    }

    @Override
    protected void initData() {

    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, PlayOverActivity.class);
        context.startActivity(intent);
    }



    @OnClick({R.id.btn_attention, R.id.btn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_attention:
                break;
            case R.id.btn_close:
                MainActivity.startToActivity(this);
                break;
        }
    }
}
