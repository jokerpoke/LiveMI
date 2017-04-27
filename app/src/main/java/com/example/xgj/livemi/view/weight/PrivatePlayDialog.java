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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.xgj.livemi.R;

/**
 * Created by chen on 2017/4/21.
 */

public class PrivatePlayDialog extends DialogFragment implements View.OnClickListener {

    private View view;


    private ImageView ivTop;
    private ImageView ivClose;
    private ImageView ivImg1;
    private LinearLayout llText;
    private ImageView ivDredge;

    private LinearLayout ll_dialogbg;
    private RelativeLayout rl_base;

    private PriPlayCallBack priPlayCallBack;

    public void setPriPlayCallBack(PriPlayCallBack priPlayCallBack) {
        this.priPlayCallBack = priPlayCallBack;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_DialogWhenLarge_NoActionBar);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_privateplay, container);
        getDialog().getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        ivTop = (ImageView) view.findViewById(R.id.iv_top);
        ivClose = (ImageView) view.findViewById(R.id.iv_close);
        ivImg1 = (ImageView) view.findViewById(R.id.iv_img1);
        llText = (LinearLayout) view.findViewById(R.id.ll_text);
        ivDredge = (ImageView) view.findViewById(R.id.iv_dredge);
        rl_base= (RelativeLayout) view.findViewById(R.id.rl_base);
        ll_dialogbg= (LinearLayout) view.findViewById(R.id.ll_dialogbg);

        ivDredge.setOnClickListener(this);
        ivClose.setOnClickListener(this);

        rl_base.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int top = ll_dialogbg.getTop();
                int bottom = ll_dialogbg.getBottom();
//                int right = ll_dialogbg.getRight();
//                int left = ll_dialogbg.getLeft();
                int touchY = (int) event.getY();
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (touchY>bottom||touchY<top){
                        dismiss();
                    }
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.iv_dredge:
                priPlayCallBack.Dredge();
                dismiss();
                break;
        }
    }

    public interface PriPlayCallBack {
        void Dredge();//开通
    }
}
