package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_status)
    TextView tvTitleStatus;
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
//                if (etPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
//                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
//                    Toast.makeText(this, "nihao", Toast.LENGTH_SHORT).show();
//                } else if (etPassword.getInputType() == InputType.TYPE_CLASS_TEXT) {
//                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                    Toast.makeText(this, "buhai ", Toast.LENGTH_SHORT).show();
//
//                }
                break;
            case R.id.btn_submit:
                //提交数据,判断各个数据是否都正常，如长度等
                //                if (!TextUtils.isEmpty(etPhone.getText())&&etPhone.getText().equals("")){
                //                etPhone.getText().length()
                break;
        }
    }
}
