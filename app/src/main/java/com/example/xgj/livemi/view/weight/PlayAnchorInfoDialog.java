package com.example.xgj.livemi.view.weight;


import android.content.Context;
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
import android.widget.TextView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.utils.GlideUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chen on 2017/4/21.
 */

public class PlayAnchorInfoDialog extends DialogFragment implements View.OnTouchListener, View.OnClickListener {
    private View view;


    private CircleImageView civUserImg;
    private TextView tvUsername;
    private ImageView ivRank;
    private TextView tvUserid;
    private TextView tv_attentionNum;
    private TextView tvFuhaozhi;
    private TextView tvFans;
    private TextView tvMeilizhi;
    private TextView tvGetwechatOrQQ;
    private TextView tv_add_attention;

    private OnListenCallBcak onListenCallBcak;

    public void setOnListenCallBcak(OnListenCallBcak onListenCallBcak) {
        this.onListenCallBcak = onListenCallBcak;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_DialogWhenLarge_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_playanthor, container);

        getDialog().getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        civUserImg = (CircleImageView) view.findViewById(R.id.civ_userImg);
        tvUsername = (TextView) view.findViewById(R.id.tv_username);
        ivRank = (ImageView) view.findViewById(R.id.iv_rank);
        tvUserid = (TextView) view.findViewById(R.id.tv_userid);
        tv_attentionNum = (TextView) view.findViewById(R.id.tv_attentionNum);
        tvFuhaozhi = (TextView) view.findViewById(R.id.tv_fuhaozhi);
        tvFans = (TextView) view.findViewById(R.id.tv_fans);
        tvMeilizhi = (TextView) view.findViewById(R.id.tv_meilizhi);
        tvGetwechatOrQQ = (TextView) view.findViewById(R.id.tv_getwechatOrQQ);
        tv_add_attention = (TextView) view.findViewById(R.id.tv_add_attention);

        tv_add_attention.setOnClickListener(this);
        tvGetwechatOrQQ.setOnClickListener(this);
        view.setOnTouchListener(this);
        return view;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int bottom = view.findViewById(R.id.ll_base).getBottom();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (y > bottom) {
                dismiss();
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_getwechatOrQQ:
                onListenCallBcak.getAnchorInfo();
                dismiss();
                break;
            case R.id.tv_add_attention:
                onListenCallBcak.addAeetntion();
                dismiss();
                break;
        }
    }

    public interface OnListenCallBcak {
        void addAeetntion();

        void getAnchorInfo();
    }

    //把拿到的数据set进来，到时候传一个bean或者list进来，这样set方法参数可以少写一点
    public void setCivUserImg(Context context, String url, CircleImageView civUserImg) {
        GlideUtil.loadCommon(context, url, civUserImg);
    }

    public void setTvUsername(TextView tvUsername) {
        this.tvUsername = tvUsername;
    }

    public void setIvRank(ImageView ivRank) {
        this.ivRank = ivRank;
    }

    public void setTvUserid(TextView tvUserid) {
        this.tvUserid = tvUserid;
    }

    public void setTv_attentionNum(TextView tv_attentionNum) {
        this.tv_attentionNum = tv_attentionNum;
    }

    public void setTvFuhaozhi(TextView tvFuhaozhi) {
        this.tvFuhaozhi = tvFuhaozhi;
    }

    public void setTvFans(TextView tvFans) {
        this.tvFans = tvFans;
    }

    public void setTvMeilizhi(TextView tvMeilizhi) {
        this.tvMeilizhi = tvMeilizhi;
    }
}
