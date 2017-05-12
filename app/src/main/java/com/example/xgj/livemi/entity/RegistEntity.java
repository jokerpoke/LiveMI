package com.example.xgj.livemi.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by chen on 2017/5/12.
 */

public class RegistEntity extends BmobObject {
    private String username;
    private  String password;
    private int mobliephone;
    private String email;
    private int qqnumber;

    public int getQqnumber() {
        return qqnumber;
    }

    public void setQqnumber(int qqnumber) {
        this.qqnumber = qqnumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobliephone(int mobliephone) {
        this.mobliephone = mobliephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
