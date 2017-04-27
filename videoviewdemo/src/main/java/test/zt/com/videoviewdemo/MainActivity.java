package test.zt.com.videoviewdemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 *
 * VideoView = MediaPlayer + SurfaceView
 */
public class MainActivity extends AppCompatActivity {
    private String url2="http://biggame1.b0.upaiyun.com/video/a9d366a3c18911e631bf9327d458feb9.mp4";

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = ((VideoView) findViewById(R.id.activity_main_videoViewId));
        //设置系统提供的控制条
        videoView.setMediaController(new MediaController(this));
        //设置url地址
        videoView.setVideoPath(url2);
        //设置准备监听
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

    }
}
