package com.example.xgj.livemi.view.ui;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseActivity;

public class MyDynamicStateActivity extends BaseActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_dynamic_state);
//    }

    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_my_dynamic_state;
    }

    @Override
    protected String initTitle() {
        return "我的动态";
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
}
