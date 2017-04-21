package com.example.xgj.livemi.view.ui;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.alibaba.view.BubblingView;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.utils.ShowToastUtils;
import com.example.xgj.livemi.view.BaseActivity;
import com.example.xgj.livemi.view.weight.PlayAnchorInfo;

import butterknife.BindView;
import butterknife.OnClick;

public class PlayActivity extends BaseActivity {


    @BindView(R.id.iv_bguser)
    ImageView ivBguser;
    @BindView(R.id.pb_bar)
    ProgressBar pbBar;
    @BindView(R.id.fl_bg)
    FrameLayout flBg;
    @BindView(R.id.vv_media)
    VideoView vvMedia;
    @BindView(R.id.iv_anchorInfo)
    TextView ivAnchorInfo;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_gift)
    ImageView ivGift;
    @BindView(R.id.iv_sendmessage)
    ImageView ivSendmessage;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_privateLive)
    ImageView ivPrivateLive;
    @BindView(R.id.iv_zan)
    ImageView ivZan;
    @BindView(R.id.bubbling_view)
    BubblingView bubblingView;
    @BindView(R.id.rl_live)
    RelativeLayout rlLive;

    //弹出输入聊天窗口
    @BindView(R.id.rl_message_input)
    LinearLayout rl_message_input;
    @BindView(R.id.iv_hide_input)
    ImageView iv_hide_input;//关闭输入框
    @BindView(R.id.iv_isShow_tanmu)
    ImageView iv_isShow_tanmu;//提示是否发送弹幕
    @BindView(R.id.et_message)
    EditText et_message;
    @BindView(R.id.tv_send)
    TextView tv_send;//发送按钮


    private boolean ishideTanMu = true;


    int i = 0;
    private int[] images = {
            R.drawable.heart0,
            R.drawable.heart1,
            R.drawable.heart2,
            R.drawable.heart3,
            R.drawable.heart4,
            R.drawable.heart5,
            R.drawable.heart6,
            R.drawable.heart7,
            R.drawable.heart8
    };
    private PlayAnchorInfo playAnchorInfo;


    @Override
    protected Object getLayoutResIdOrView() {
        return R.layout.activity_play;
    }

    @Override
    protected String initTitle() {
        return null;
    }

    @Override
    protected void onActivityPrepared() {

    }

    @Override
    protected void initIntentData() {

    }

    @Override
    protected void initView() {
        playAnchorInfo = new PlayAnchorInfo();
    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                flBg.setVisibility(View.GONE);
                rlLive.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }


    @OnClick({R.id.iv_anchorInfo, R.id.iv_close, R.id.iv_gift, R.id.iv_sendmessage, R.id.iv_share,
            R.id.iv_privateLive, R.id.iv_zan
            , R.id.iv_hide_input, R.id.tv_send, R.id.iv_isShow_tanmu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_anchorInfo:
                initDialog();
                break;
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_gift:
                break;
            case R.id.iv_sendmessage:
                if (rl_message_input.getVisibility() == View.GONE) {
                    rl_message_input.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.iv_share:
                break;
            case R.id.iv_privateLive:
                break;
            case R.id.iv_zan:
                bubblingView.addBubblingItem(images[i++ % 5]);
                break;
            case R.id.iv_hide_input:
                rl_message_input.setVisibility(View.GONE);
                break;
            case R.id.tv_send:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_message.getWindowToken(), 0);
                et_message.setText("");
                et_message.setHint("跟主播聊什么?");
                break;
            case R.id.iv_isShow_tanmu:
                //图片的转换操作
                if (ishideTanMu) {//弹弹幕
                    ishideTanMu = !ishideTanMu;
                    iv_isShow_tanmu.setImageResource(R.mipmap.ic_launcher_round);
                } else {//不弹
                    ishideTanMu = !ishideTanMu;
                    iv_isShow_tanmu.setImageResource(R.mipmap.ic_launcher);
                }
                break;
        }
    }

    /**
     * 初始化主播信息dialog
     */
    private void initDialog() {
        FragmentManager fragmentManager = getFragmentManager();

        playAnchorInfo.show(fragmentManager, "iv_anchorInfo");
        playAnchorInfo.setOnListenCallBcak(new PlayAnchorInfo.OnListenCallBcak() {
            @Override
            public void addAeetntion() {
                //请求接口
                ShowToastUtils.showToast(PlayActivity.this, "fff");
            }

            @Override
            public void getAnchorInfo() {
                //请求接口
            }
        });
    }

}
