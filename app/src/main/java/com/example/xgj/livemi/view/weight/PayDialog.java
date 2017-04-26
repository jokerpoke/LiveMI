package com.example.xgj.livemi.view.weight;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.xgj.livemi.R;

/**
 * Created by chen on 2017/4/20.
 */

public class PayDialog extends DialogFragment implements View.OnClickListener, View.OnTouchListener {
    private View view;
    private RelativeLayout rl_wechatpay, rl_alipay;
    private PayDialogCallBack payDialogCallBack;


    public void setPayDialogCallBack(PayDialogCallBack payDialogCallBack) {
        this.payDialogCallBack = payDialogCallBack;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_DialogWhenLarge_NoActionBar);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pay_dialog, container);
        rl_wechatpay = (RelativeLayout) view.findViewById(R.id.rl_wechatpay);
        rl_alipay = (RelativeLayout) view.findViewById(R.id.rl_alipay);
        getDialog().getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        rl_alipay.setOnClickListener(this);
        rl_wechatpay.setOnClickListener(this);
        view.setOnTouchListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_wechatpay:
                payDialogCallBack.payWeChat();
                dismiss();
                break;
            case R.id.rl_alipay:
                payDialogCallBack.payAli();
                dismiss();
                break;
        }
    }

    //点击阴影消失
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int top = view.findViewById(R.id.ll_base).getTop();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (y < top) {
                dismiss();
            }
        }
        return false;
    }

    public interface PayDialogCallBack {
        void payWeChat();

        void payAli();
    }
}
