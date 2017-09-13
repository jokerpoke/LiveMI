package com.example.xgj.livemi.textpack;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.xgj.livemi.R;

/**
 * 当用户多长时间没触摸屏幕是显示屏保Demo
 */
public class TestAdTimeActivity extends AppCompatActivity {
    private static final String Tag="TestAdTimeActivity";
    private LinearLayout linlayout;

//    private Date startTime;
//    private Date endTime;
    private long startTime;
    private long endTime;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           if (msg.what==1){
//               System.currentTimeMillis();
               endTime=System.currentTimeMillis();
               if (endTime-startTime>=time){
                   Log.d(Tag, "handleMessage: "+"成功了");
               }
           }
        }
    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };
    private long time = 1000 * 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ad_time);
//        linlayout = (LinearLayout) this.findViewById(R.id.linlayout);
//        linlayout.setOnTouchListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Log.d(Tag,"up");
                handler.sendEmptyMessageDelayed(1,1000*1);

                break;
            case MotionEvent.ACTION_DOWN:
                Log.d(Tag, "onTouchEvent: ");
                startTime=System.currentTimeMillis();
                handler.removeCallbacks(runnable);
//                handler.postDelayed(runnable,1000*5);
                break;

        }
        return super.onTouchEvent(event);
    }


}
