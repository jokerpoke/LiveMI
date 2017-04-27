package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.utils.ShowToastUtils;
import com.example.xgj.livemi.view.BaseActivity;
import com.example.xgj.livemi.view.fragment.MainFragment;
import com.example.xgj.livemi.view.fragment.MyPersonFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.tv_main)
    TextView tvMain;
    @BindView(R.id.tv_live)
    TextView tvLive;
    @BindView(R.id.tv_myinfo)
    TextView tvMyinfo;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.iv_live)
    ImageView ivLive;

    private long preTime;

    private MainFragment mainFragment;
    private MyPersonFragment myPersonFragment;
    private int currentId = R.id.tv_main;// 当前选中id,默认是主页


    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_main);
    //    }

    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_main;
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
        tvMain.setSelected(true);
        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, mainFragment).commit();

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_main, R.id.tv_myinfo, R.id.iv_live})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_main:
                if (v.getId() != currentId) {//如果当前选中跟上次选中的一样,不需要处理
                    changeSelect(v.getId());//改变图标跟文字颜色的选中
                    changeFragment(v.getId());//fragment的切换
                    currentId = v.getId();//设置选中id
                }
                break;
            case R.id.tv_myinfo:
                if (v.getId() != currentId) {//如果当前选中跟上次选中的一样,不需要处理
                    changeSelect(v.getId());//改变图标跟文字颜色的选中
                    changeFragment(v.getId());//fragment的切换
                    currentId = v.getId();//设置选中id
                }
                break;
            case R.id.iv_live:
                ParperPlayActivity.startToActivity(this);
                break;
        }
    }


    /**
     * 改变fragment的显示
     *
     * @param resId
     */
    private void changeFragment(int resId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//开启一个Fragment事务

        hideFragments(transaction);//隐藏所有fragment
        if (resId == R.id.tv_main) {//主页
            if (mainFragment == null) {//如果为空先添加进来.不为空直接显示
                mainFragment = new MainFragment();
                transaction.add(R.id.main_container, mainFragment);
            } else {
                transaction.show(mainFragment);
            }
        } else if (resId == R.id.tv_myinfo) {//动态
            if (myPersonFragment == null) {
                myPersonFragment = new MyPersonFragment();
                transaction.add(R.id.main_container, myPersonFragment);
            } else {
                transaction.show(myPersonFragment);
            }
        }
        transaction.commit();//一定要记得提交事务
    }

    /**
     * 显示之前隐藏所有fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mainFragment != null)//不为空才隐藏,如果不判断第一次会有空指针异常
            transaction.hide(mainFragment);
        if (myPersonFragment != null)
            transaction.hide(myPersonFragment);
    }

    /**
     * 改变TextView选中颜色
     *
     * @param resId
     */
    private void changeSelect(int resId) {
        tvMain.setSelected(false);
        tvMyinfo.setSelected(false);

        switch (resId) {
            case R.id.tv_main:
                tvMain.setSelected(true);
                break;
            case R.id.tv_myinfo:
                tvMyinfo.setSelected(true);
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //如果用户按的是返回键
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-preTime>3000) {
                ShowToastUtils.showToast(this,"在按一次返回键退出!");
                preTime = System.currentTimeMillis();
            }else{
                finishAffinity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}




