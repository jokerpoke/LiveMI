package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class EarningActivity extends BaseActivity {


    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_earning;
    }

    @Override
    protected String initTitle() {
        return "我的收益";
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


    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        //提交数据操作
    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, EarningActivity.class);
        context.startActivity(intent);
    }
}
