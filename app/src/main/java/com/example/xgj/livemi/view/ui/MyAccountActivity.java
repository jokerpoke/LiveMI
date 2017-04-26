package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseActivity;
import com.example.xgj.livemi.view.weight.PayDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class MyAccountActivity extends BaseActivity {


    @BindView(R.id.tv_moneyNum)
    TextView tvMoneyNum;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.iv_diamond)
    ImageView ivDiamond;
    @BindView(R.id.tv_68vip)
    TextView tv68vip;
    @BindView(R.id.iv_becomemember)
    TextView ivBecomemember;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_addmoney30)
    TextView tvAddmoney30;
    @BindView(R.id.tv_addmoney50)
    TextView tvAddmoney50;
    @BindView(R.id.tv_addmoney100)
    TextView tvAddmoney100;
    @BindView(R.id.tv_addmoney200)
    TextView tvAddmoney200;
    @BindView(R.id.tv_addmoney500)
    TextView tvAddmoney500;
    @BindView(R.id.tv_contactus)
    TextView tvContactus;
    private PayDialog payDialog;
    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();



    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_my_account;
    }

    @Override
    protected String initTitle() {
        return "我的账户";
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

    @OnClick({R.id.iv_becomemember, R.id.tv_addmoney30, R.id.tv_addmoney50, R.id.tv_addmoney100, R.id.tv_addmoney200, R.id.tv_addmoney500, R.id.tv_contactus})
    public void onViewClicked(View view) {
        if (payDialog == null) {
            payDialog = new PayDialog();
        }
        switch (view.getId()) {
            case R.id.iv_becomemember:
                showPayDialog(68);
                break;
            case R.id.tv_addmoney30:
                showPayDialog(30);
                break;
            case R.id.tv_addmoney50:
                showPayDialog(50);
                break;
            case R.id.tv_addmoney100:
                showPayDialog(100);
                break;
            case R.id.tv_addmoney200:
                showPayDialog(200);
                break;
            case R.id.tv_addmoney500:
                showPayDialog(500);
                break;
            case R.id.tv_contactus:
                ContactusActivity.startToActivity(this);
                break;
        }

    }

    /**
     * 显示弹窗，不同价位的传递给支付宝或者微信去支付
     *
     * @param i
     */
    public void showPayDialog(final int i) {


            payDialog.show(fragmentManager, "iv_becomemember");
            payDialog.setPayDialogCallBack(new PayDialog.PayDialogCallBack() {
                @Override
                public void payWeChat() {
                    switch (i) {
                        case 68://开通会员
                            break;
                        case 30:
                            break;
                        case 50:
                            break;
                        case 100:
                            break;
                        case 200:
                            break;
                        case 500:
                            break;

                    }
                }

                @Override
                public void payAli() {
                    switch (i) {
                        case 68://开通会员
                            break;
                        case 30:
                            break;
                        case 50:
                            break;
                        case 100:
                            break;
                        case 200:
                            break;
                        case 500:
                            break;

                    }
                }
            });

    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, MyAccountActivity.class);
        context.startActivity(intent);
    }
//
//    public static void startToActivityFromDialog(Context context) {
//        Intent intent = new Intent(context, MyAccountActivity.class);
//        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//    }
}
