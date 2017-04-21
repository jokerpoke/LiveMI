package com.example.xgj.livemi.view.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseFragment;
import com.example.xgj.livemi.view.ui.ContactusActivity;
import com.example.xgj.livemi.view.ui.EarningActivity;
import com.example.xgj.livemi.view.ui.MyAccountActivity;
import com.example.xgj.livemi.view.ui.RegistActivity;
import com.example.xgj.livemi.view.ui.SearchActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPersonFragment extends BaseFragment {


    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.civ_userImg)
    CircleImageView civUserImg;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.ll_fans)
    LinearLayout llFans;
    @BindView(R.id.tv_attention)
    TextView tvAttention;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rl_mydynamicstate)
    RelativeLayout rlMydynamicstate;
    @BindView(R.id.iv_arror)
    ImageView ivArror;
    @BindView(R.id.rl_myrank)
    RelativeLayout rlMyrank;
    @BindView(R.id.iv_arror2)
    ImageView ivArror2;
    @BindView(R.id.rl_myaccount)
    RelativeLayout rlMyaccount;
    @BindView(R.id.rl_register)
    RelativeLayout rlRegister;
    @BindView(R.id.iv_arror3)
    ImageView ivArror3;
    @BindView(R.id.rl_myearning)
    RelativeLayout rlMyearning;
    @BindView(R.id.rl_lately)
    RelativeLayout rlLately;
    @BindView(R.id.rl_service)
    RelativeLayout rlService;
    Unbinder unbinder;

    public MyPersonFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_person;
    }


    @OnClick({R.id.iv_search, R.id.iv_message, R.id.civ_userImg, R.id.tv_username, R.id.ll_fans, R.id.tv_attention, R.id.tv_praise, R.id.rl_top, R.id.rl_mydynamicstate, R.id.rl_myrank, R.id.rl_myaccount, R.id.rl_register, R.id.rl_myearning, R.id.rl_lately, R.id.rl_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                SearchActivity.startToActivity(getContext());
                break;
            case R.id.iv_message:
                break;
            case R.id.civ_userImg:
                break;
            case R.id.tv_username:
                break;
            case R.id.ll_fans:
                break;
            case R.id.tv_attention:
                break;
            case R.id.tv_praise:
                break;
            case R.id.rl_top:
                break;
            case R.id.rl_mydynamicstate:
                break;
            case R.id.rl_myrank:
                break;
            case R.id.rl_myaccount:
                MyAccountActivity.startToActivity(getContext());
                break;
            case R.id.rl_register:
                RegistActivity.startToActivity(getContext());
                break;
            case R.id.rl_myearning:
                EarningActivity.startToActivity(getContext());
                break;
            case R.id.rl_lately://最近访客,跳转钱要先判断有没人数据，没有就弹toast，有才跳转到界面
                break;
            case R.id.rl_service:
                ContactusActivity.startToActivity(getContext());
                break;
        }
    }
}
