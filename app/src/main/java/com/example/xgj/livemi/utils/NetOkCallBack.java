package com.example.xgj.livemi.utils;

import android.content.Context;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by chen on 2017/4/1.
 */

public abstract class NetOkCallBack implements Callback {
    //    InnCallback innCallback;
    Context mContext;

    //    public void setInnCallback(InnCallback innCallback, Context mContext) {
    //        this.innCallback = innCallback;
    //        this.mContext = mContext;
    //    }

    @Override
    public void onFailure(Call call, IOException e) {
        failure(call, e);
    }



    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
//            try {
//                JSONObject jsonObject = new JSONObject(response.body().string());
//                int status = jsonObject.optInt("status");
//                if (status == 0) {
//                    ToastUtils.showToast("没有数据", mContext);
//                } else if (status == 1) {
                    success(call, response.body().string());
                }
                call.cancel();//请求成功后取消请求
//            } catch (JSONException e) {
//                e.printStackTrace();
            }

//        }
//    }


    public abstract void success(Call call, String json);

    public abstract void failure(Call call, IOException e);

}
