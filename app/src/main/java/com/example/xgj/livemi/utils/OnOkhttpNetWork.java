package com.example.xgj.livemi.utils;

import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by chen on 2017/3/31.
 */

public class OnOkhttpNetWork {

    private OkHttpClient mOkHttpClient;

    //私有构造
    private OnOkhttpNetWork() {
        //初始化Okhttp配制
        initOkHttpFunction();
    }

    //提供实例化对象
    public static OnOkhttpNetWork getUtilsInstance() {
        return OkHttpSingle.sCommonOkhttpUtils;
    }

    //实例静态内部类
    private static class OkHttpSingle {
        private static OnOkhttpNetWork sCommonOkhttpUtils = new OnOkhttpNetWork();
    }

    //    //获取String的方法
    //    public static String getString() {
    //        return "single";
    //    }


    //初始化Okhttp配制
    private void initOkHttpFunction() {
        //配制网络数据缓存
        File cacheDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        //配制网络数据缓存大小  10M
        int cacheSize = 10 * 1024 * 1024;
        //
        Cache cache = new Cache(cacheDirectory, cacheSize);
        //创建HttpClientBuilder
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        //设置缓存 以及超时间 以及获取OkHttpClient对象
        mOkHttpClient = builder.writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }


    /**
     * 异步POST网络请求  提交表单数据
     *
     * @param url          请求网络链接
     * @param headerMap    请求网络添加头信息
     * @param parmMap      请求网络参数
     * @param cacheControl 缓存模式
     *                     public static final CacheControl FORCE_NETWORK = ..;
     *                     <p>
     *                     public static final CacheControl FORCE_CACHE = ...
     * @param callback     响应回调
     * @param tag          网络任务标识
     */
    public void okPostFormRequest(String url,String method, Map<String, String> headerMap, Map<String, String> parmMap, CacheControl cacheControl, NetOkCallBack callback, String tag) {
        //POST - FORM 表单 获取请求参数BODY
        FormBody.Builder formBuilder = new FormBody.Builder();


        //需要加密数据是使用如下方式去添加参数传递给后台
        //Map<String, String> parmMap  方法参数改为 JSONObject jParams
//        Map<String,String> maps=OnOkhttpNetWork.getBaseHttpPostParams(method,jParams); //循环传递该map参数给后台

        //POST - FORM 表单 封装参数
        if (parmMap != null && !parmMap.isEmpty()) {
            for (Map.Entry<String, String> entry : parmMap.entrySet()) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        //POST - FORM 获取表单BODY
        RequestBody formBody = formBuilder.build();
        //POST - FORM 构建请求Request Builder
        Request.Builder requestBuilder = new Request.Builder().url(url);

        //POST - FORM 设置缓存

        if (cacheControl != null) {
            requestBuilder.cacheControl(cacheControl);
        }

        //POST - FORM 添加请求头信息
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry :
                    headerMap.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        //POST - FORM 构建请求Request
        Request request = requestBuilder.post(formBody).build();
        //POST - FORM 构建请求响应Call
        Call call = mOkHttpClient.newCall(request);
        //POST - FORM 设置取消任务标签
        //        mCallHashMap.put(tag,call);
        //POST - FORM 发起请求
        call.enqueue(callback);
    }

  /*  *//**
     * 移除 取消网络任务
     *
     * @param tag
     *//*
    public void removeRequest(String tag) {
        //获取KEY的集合
        Set<Map.Entry<String, Call>> keyEntries = mCallHashMap.entrySet();
        //如果包含KEY
        if (keyEntries.contains(tag)) {
            //获取对应的Call
            Call call = mCallHashMap.get(tag);
            //如果没有被取消 执行取消的方法
            if (!call.isCanceled()) {
                call.cancel();
            }
            //移除对应的KEY
            mCallHashMap.remove(tag);
        }
    }*/
    //}


    /**
     * 异步POST网络请求 提交JSON数据
     *
     * @param url          请求网络链接
     * @param headerMap    请求网络添加头信息
     * @param parmMap      请求网络参数
     * @param cacheControl 缓存模式
     *                     public static final CacheControl FORCE_NETWORK = ..;
     *                     <p>
     *                     public static final CacheControl FORCE_CACHE = ...
     * @param callback     响应回调
     * @param tag          网络任务标识
     */
    public void okPostJSONRequestJSON(String url, Map<String, String> headerMap, Map<String, String> parmMap, CacheControl cacheControl, Callback callback, String tag) {
        //获取Builder
        Request.Builder builder = new Request.Builder().url(url);
        // 设置缓存
        if (cacheControl != null) {
            builder.cacheControl(cacheControl);
        }

        //添加请求头信息
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        /**
         * 封装参数
         */
        JSONObject jsonObject = new JSONObject();
        if (parmMap != null && !parmMap.isEmpty()) {
            for (Map.Entry<String, String> entry : parmMap.entrySet()) {
                try {
                    jsonObject.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //获取RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        //获取 Request
        Request request = builder.post(requestBody).build();
        //获取Call
        Call call = mOkHttpClient.newCall(request);
        //POST - JSON 设置取消任务标签
        //        JSONObject mCallHashMap;
        //        mCallHashMap.put(tag,call);
        //POST - JSON 发起请求
        call.enqueue(callback);
    }
   /* *//**
     * 移除 取消网络任务
     *
     * @param tag
     *//*
    public void removeRequest(String tag) {
        //获取KEY的集合
        Set<Map.Entry<String, Call>> keyEntries = mCallHashMap.entrySet();
        //如果包含KEY
        if (keyEntries.contains(tag)) {
            //获取对应的Call
            Call call = mCallHashMap.get(tag);
            //如果没有被取消 执行取消的方法
            if (!call.isCanceled()) {
                call.cancel();
            }
            //移除对应的KEY
            mCallHashMap.remove(tag);
        }
    }*/

    /**
     * 请求加密处理
     * @param method
     * @param jParams
     * @return
     */
    public static Map<String, String> getBaseHttpPostParams(String method, JSONObject jParams) {
        Map<String, String> params = new HashMap<>();
//        params.put("appkey", BKBaseApplication.getInstance().getBKAppKey());
        params.put("method", method);
        params.put("timestamp", "" + (System.currentTimeMillis() / 1000));
        params.put("data", jParams.toString());
        sign(params);
        return params;
    }

    public static void sign(Map<String, String> params) {
        String sign = "";
        ArrayList<String> paramArray = new ArrayList<>();
        for (String key : params.keySet()) {
            String param = key + params.get(key);
            paramArray.add(param);
        }
        Collections.sort(paramArray);
        for (String param : paramArray) {
            sign += param;
        }
//        sign = BKBaseApplication.getInstance().getBKAppSecret() + sign + BKBaseApplication.getInstance().getBKAppSecret();
//        sign = BKMD5.stringToMD5(sign);
        params.put("sign", sign);
    }

}
