package com.example.xgj.livemi.textpack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.utils.DownPicUtil;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveImgActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.iv_img)
    ImageView iv_img;
    Bitmap bitmap;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String picFile = (String) msg.obj;
            String[] split = picFile.split("/");
            String fileName = split[split.length - 1];
            try {
                MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), picFile, fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + picFile)));
            Toast.makeText(SaveImgActivity.this, "图片保存图库成功", Toast.LENGTH_LONG).show();
        }
    };
    String url = "http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_img);
        ButterKnife.bind(this);
        try {
            bitmap = Glide.with(this)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ;
        iv_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DownPicUtil.downPic(bitmap, new DownPicUtil.DownFinishListener() {

            @Override
            public void getDownPath(String s) {
                Toast.makeText(SaveImgActivity.this, "下载完成", Toast.LENGTH_LONG).show();
                Message msg = Message.obtain();
                msg.obj = s;
                handler.sendMessage(msg);
            }
        });
    }
}
