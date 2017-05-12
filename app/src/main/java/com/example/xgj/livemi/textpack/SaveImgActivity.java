package com.example.xgj.livemi.textpack;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.utils.DownPicUtil;
import com.example.xgj.livemi.utils.GlideUtil;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveImgActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.iv_img)
    ImageView iv_img;
    Bitmap bitmap;
    String url = "http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_img);
        ButterKnife.bind(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bitmap = Glide.with(SaveImgActivity.this)
                            .load(url)
                            .asBitmap()
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    iv_img.setOnClickListener(SaveImgActivity.this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        GlideUtil.loadCommon(this, url, iv_img);


    }

    @Override
    public void onClick(View v) {
        DownPicUtil.downPicBitmap(SaveImgActivity.this,bitmap, new DownPicUtil.DownFinishListener() {

            @Override
            public void getDownPath(String s) {
                Toast.makeText(SaveImgActivity.this, "下载完成", Toast.LENGTH_LONG).show();
            }
        });
    }
}
