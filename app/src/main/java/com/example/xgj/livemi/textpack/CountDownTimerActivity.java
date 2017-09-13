package com.example.xgj.livemi.textpack;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xgj.livemi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountDownTimerActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    @BindView(R.id.CountDownTimerActivity_tv_time)
    TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        ButterKnife.bind(this);
        countDownTimer = new CountDownTimer(5 * 1000, 1 * 1000-10) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_time.setText("还剩下" + (millisUntilFinished+15) / 1000);
            }

            @Override
            public void onFinish() {
                tv_time.setText("结束");
//                countDownTimer.cancel();
//                countDownTimer.onFinish();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
