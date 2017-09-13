package com.example.xgj.livemi.textpack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.utils.LogsUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main3Activity extends AppCompatActivity  {
    @BindView(R.id.iv_img_main3)
    ImageView iv_img_main3;
    @BindView(R.id.btn_main3)
    Button btn_main3;
    @BindView(R.id.et_text)
    EditText et_text;

    private static final String TAG = "TAG";
    private static final String url = "http://vipfacaiflvbceshi.com/captcha?t=1499735813380";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        new Thread() {
            @Override
            public void run() {
                super.run();
                get();
            }
        }.start();

        //        OnOkhttpNetWork.getUtilsInstance().okPostJSONRequestJSON(url, null, null, null, this, null);
//    }
//
//    @Override
//    public void onFailure(Call call, IOException e) {
//
//    }
//
//    @Override
//    public void onResponse(Call call, Response response) throws IOException {
//        String string = response.body().string();
//        LogsUtils.d("Main3Activity=====",string);
//
////        List<String> values = response.headers().values("Set-Cookie");
////        String s = values.get(0);
////        LogsUtils.e("Set-Cookie=",s);
////        String substring = s.substring(0, s.indexOf(";"));
////        LogsUtils.d("substring=",substring);
    }


    private void get() {
        OkHttpClient okHttpClient = new OkHttpClient();
        //构建一个请求对象
        Request request = new Request.Builder().url(url).header("Content-Type", "text/html; charset=utf-8").build();
        //发送请求
        try {
            Response response = okHttpClient.newCall(request).execute();
            //打印服务端传回的数据
            final String string = response.body().string();
            Log.i("main3===", string);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String msg_client = new String(string.getBytes(), "utf-8");
                        Glide.with(Main3Activity.this).load(msg_client).into(iv_img_main3);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            });
            Headers headers = response.headers();
            LogsUtils.d("main3===","headers=="+headers);

            List<String> cookies = headers.values("Set-Cookie");
            String session = cookies.get(0);
            LogsUtils.d("main3===","onResponse-size: " + cookies);
           String  s = session.substring(0, session.indexOf(";"));
            Log.i("main3===", "session is  :" + s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
