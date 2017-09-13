package com.example.xgj.livemi.utils;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.xgj.livemi.R;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

/**
 * Created by chen on 2017/4/17.
 */

public class MyApp extends Application {
    public static MyApp mMyApp;
    private RefWatcher refWatcher;

    private Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this);
        this.mMyApp = this;
        initGson();
        refWatcher = LeakCanary.install(this);
    }

    public void initShareSdk() {
        //        OnekeyShare oks = new OnekeyShare();
        //        //关闭sso授权
        //        oks.disableSSOWhenAuthorize();
        //        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        //        oks.setTitle("标题");
        //        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        //        oks.setTitleUrl("http://sharesdk.cn");
        //        // text是分享文本，所有平台都需要这个字段
        //        oks.setText("我是分享文本");
        //        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        //        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        //        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        //        // url仅在微信（包括好友和朋友圈）中使用
        //        oks.setUrl("http://sharesdk.cn");
        //        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //        oks.setComment("我是测试评论文本");
        //        // site是分享此内容的网站名称，仅在QQ空间使用
        //        oks.setSite("ShareSDK");
        //        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        //        oks.setSiteUrl("http://sharesdk.cn");
        //
        //        // 启动分享GUI
        //        oks.show(this);


        //        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, cn.sharesdk.framework.Platform.ShareParams paramsToShare) {
                if ("QZone".equals(platform.getName())) {
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                }
                if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setUrl(null);
                    paramsToShare.setText("分享文本 http://www.baidu.com");
                }
                if ("Wechat".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }

            }
        });

        // 启动分享GUI
        oks.show(this);
    }


    private void initGson() {
        if (gson == null) {
            gson = new Gson();
        }
    }


    public Gson getGson() {
        return gson;
    }

    public static RefWatcher getRefWatcher() {
        return MyApp.getmMyApp().refWatcher;
    }

    public static MyApp getmMyApp() {
        return mMyApp;
    }

    public void downLoads(String downloadUrl, Activity activity) {
        DownloadUtils downloadUtils = new DownloadUtils(activity);
        downloadUtils.downloadAPK(downloadUrl, "XXX.apk");
    }

    //    public void autoFocus() {//键盘获取焦点
    //        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    //        imm.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
    //    }

}
