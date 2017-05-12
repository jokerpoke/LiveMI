package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.entity.RegistEntity;
import com.example.xgj.livemi.utils.ShowToastUtils;
import com.example.xgj.livemi.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegistActivity extends BaseActivity {


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_QQnumber)
    EditText etQQnumber;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.tv_pwd)
    TextView tvPwd;
    @BindView(R.id.iv_pwdstatus)
    ImageView ivPwdstatus;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private Boolean showPassword = true;

    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_regist;
    }

    @Override
    protected String initTitle() {
        return "注册";
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
        Intent intent = new Intent(context, RegistActivity.class);
        context.startActivity(intent);
    }


    @OnClick({R.id.iv_pwdstatus, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pwdstatus:
                if (showPassword) {//显示密码
                    showPassword = !showPassword;
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPassword.setSelection(etPassword.getText().toString().length());
                } else {//隐藏密码
                    showPassword = !showPassword;
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPassword.setSelection(etPassword.getText().toString().length());
                }

                break;
            case R.id.btn_submit:
                //提交数据,判断各个数据是否都正常，如长度等
                //                                if (!TextUtils.isEmpty(etPhone.getText())&&etPhone.getText().equals("")){
//                if (etPhone.getText().length() > 4) {
//                    ShowToastUtils.showToast(this, "hh");
//
//                }

                RegistEntity registEntity = new RegistEntity();
                registEntity.setMobliephone(Integer.valueOf(etPhone.getText().toString()));
                registEntity.setQqnumber(Integer.valueOf(etQQnumber.getText().toString()));
                registEntity.setUsername(etUsername.getText().toString());
                registEntity.setPassword(etPassword.getText().toString());
                registEntity.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            ShowToastUtils.showToast(RegistActivity.this, "注册成功");
                        }else {
                            ShowToastUtils.showToast(RegistActivity.this,"注册失败");
                        }
                    }
                });

                break;
        }
    }
}
