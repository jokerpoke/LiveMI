package com.example.xgj.livemi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xgj.livemi.utils.MyApp;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private TextView tv_titile;
    private ImageView iv_back;
    private ImageView iv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_base);
        initContentView();
        ButterKnife.bind(this);
    }

    private void initContentView() {
        Object layoutResIdOrView = getLayoutResIdOrView();
        if (layoutResIdOrView == null) {
            throw new IllegalArgumentException("layoutResIdOrView can not be null");
        }
        if (layoutResIdOrView instanceof Integer) {
            setContentView((Integer) layoutResIdOrView);
        } else {
            setContentView((View) layoutResIdOrView);
        }
    }

    //    protected void setUpActionBarView() {
    //        tv_titile = (TextView) findViewById(R.id.tv_title);
    //        iv_back = (ImageView) findViewById(R.id.iv_back);
    //        iv_search = (ImageView) findViewById(R.id.iv_search);
    //        //设置标题
    //        if (tv_titile != null) {
    //            tv_titile.setText(initTitle(resId1) == null ? "" : initTitle(resId1));
    //        }
    //        //设置返回按钮
    //        if (iv_back != null && iv_back.getVisibility() == View.VISIBLE) {
    //            iv_back.setOnClickListener(new View.OnClickListener() {
    //                @Override
    //                public void onClick(View v) {
    //                    finish();
    //                }
    //            });
    //        }
    //    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //        setUpActionBarView();
        init();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        init();
    }

    private void init() {

        initView();
        initData();
        initIntentData();
        onActivityPrepared();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApp.getRefWatcher();
        refWatcher.watch(this);
    }

    protected abstract Object getLayoutResIdOrView();

    protected abstract String initTitle(int resId);

    protected abstract void onActivityPrepared();

    protected abstract void initIntentData();

    protected abstract void initView();

    protected abstract void initData();

}
