package com.example.xgj.livemi.view.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ParperPlayActivity extends BaseActivity {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.et_input_title_name)
    EditText etInputTitleName;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.tv_friends_circle)
    TextView tvFriendsCircle;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_startplay)
    TextView tvStartplay;


    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_parper_play;
    }

    @Override
    protected String initTitle() {
        return null;
    }

    @Override
    protected void onActivityPrepared() {
        etInputTitleName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
                    //输入完成后隐藏键盘
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return false;
            }
        });
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


    @OnClick({R.id.iv_close, R.id.tv_wechat, R.id.tv_friends_circle, R.id.tv_qq, R.id.tv_startplay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_wechat:
                break;
            case R.id.tv_friends_circle:
                break;
            case R.id.tv_qq:
                break;
            case R.id.tv_startplay:
                break;
        }
    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, ParperPlayActivity.class);
        context.startActivity(intent);
    }
}
