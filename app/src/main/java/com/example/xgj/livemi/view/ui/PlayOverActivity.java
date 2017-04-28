package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
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

    @Override
    protected void initView() {

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
