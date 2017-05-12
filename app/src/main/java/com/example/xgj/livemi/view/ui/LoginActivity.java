package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.entity.RegistEntity;
import com.example.xgj.livemi.utils.ShowToastUtils;
import com.example.xgj.livemi.view.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.activity_login_et_username)
    EditText et_username;
    @BindView(R.id.activity_login_et_pwd)
    EditText et_pwd;
    @BindView(R.id.activity_login_btn_submit)
    Button btn_submit;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_status)
    TextView tvTitleStatus;

    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_login;
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
        //        ShowToastUtils.showToast(LoginActivity.this, RegistEntity.objectId.toString());
    }


    @Override
    protected void initData() {

    }

//    @OnClick({R.id.activity_login_btn_submit})
//    public void onclick(View view) {
//        switch (view.getId()) {
//            case R.id.activity_login_btn_submit:
//
//                //                bmobQuery.getObject(null, new QueryListener<RegistEntity>() {
//                //                    @Override
//                //                    public void done(RegistEntity registEntity, BmobException e) {
//                //                        if (e!=null){
//                //                            ShowToastUtils.showToast(LoginActivity.this,"查询成功"+registEntity.getUsername());
//                //                            //                            registEntity.get
//                //                        }else{
//                //                            ShowToastUtils.showToast(LoginActivity.this,"查询失败"+e.getMessage());
//                //                        }
//                //                    }
//                //                });
//                break;
//        }
//    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_login_btn_submit)
    public void onViewClicked() {
        BmobQuery<RegistEntity> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("username", et_username.getText().toString());
        bmobQuery.setLimit(10);
        bmobQuery.findObjects(new FindListener<RegistEntity>() {
            @Override
            public void done(List<RegistEntity> list, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPassword().equals(et_pwd)) {
                            ShowToastUtils.showToast(LoginActivity.this, "登录成功,即将跳转到首页!");
                            MainActivity.startToActivity(LoginActivity.this);
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
