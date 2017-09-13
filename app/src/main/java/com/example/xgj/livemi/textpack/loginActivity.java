package com.example.xgj.livemi.textpack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.xgj.livemi.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

import static android.R.attr.action;

public class loginActivity extends AppCompatActivity implements PlatformActionListener {
private String TAG="loginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }
    public void btn_login(View view) {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        getInfo(qq);

    }

    private void getInfo(Platform pla) {
        if (pla != null) {
            //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
            pla.setPlatformActionListener(this);
            //authorize与showUser单独调用一个即可
            pla.authorize();//单独授权,OnComplete返回的hashmap是空的
            pla.showUser(null);//授权并获取用户信息
            //移除授权
            //weibo.removeAccount(true);
            //
            //        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
            String accessToken = pla.getDb().getToken(); // 获取授权token
            String openId = pla.getDb().getUserId(); // 获取用户在此平台的ID
            String nickname = pla.getDb().getUserName(); // 获取用户昵称
            //部分没有封装的字段可以通过键值获取，例如下面微信的unionid字段
            //        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
            //        String unionid = wechat.getDb().get("unionid");
            // 接下来执行您要的操作
            Log.d(TAG, "btn_login:=== " + accessToken + "===" + openId + "=====" + nickname);
            return;
        }

        pla.setPlatformActionListener(this);
        //关闭SSO授权
        pla.SSOSetting(true);
        pla.showUser(null);


    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        // TODO Auto-generated method stub
        //输出所有授权信息
        platform.getDb().exportData();
        Log.d(TAG, "onComplete: " + platform.getDb().exportData());
        //遍历Map
        //简单获取res里面的用户信息
        Iterator ite = hashMap.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry entry = (Map.Entry) ite.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "： " + value);
            Log.d(TAG, "onComplete:======= " + key + "： " + value);
        }

        //用户资源都保存到res
        //通过打印res数据看看有哪些数据是你想要的
        //获取平台数据库的用户信息
        if (action == Platform.ACTION_USER_INFOR) {
            PlatformDb platDB = platform.getDb();//获取数平台数据DB
            //通过DB获取各种数据
            platDB.getToken();
            platDB.getUserGender();
            platDB.getUserIcon();
            platDB.getUserId();
            platDB.getUserName();
            Log.d(TAG, "onComplete: ===if====" + platDB.getToken() + "------" + platDB.getUserGender() + "----" + platDB.getUserName());
        }


        //        if (action == Platform.ACTION_USER_INFOR) {
        //            Message msg = new Message();
        //            msg.what = MSG_AUTH_COMPLETE;
        //            msg.obj = new Object[] {platform.getName(), res};
        //            handler.sendMessage(msg);
        //        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        if (action == Platform.ACTION_USER_INFOR) {
//            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        throwable.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        if (action == Platform.ACTION_USER_INFOR) {
//            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }



}
