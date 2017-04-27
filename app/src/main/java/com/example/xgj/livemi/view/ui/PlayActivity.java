package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
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
import com.example.xgj.livemi.entity.PlayGiftEntity;
import com.example.xgj.livemi.utils.MyApp;
import com.example.xgj.livemi.utils.ShowToastUtils;
import com.example.xgj.livemi.view.BaseActivity;
import com.example.xgj.livemi.view.weight.PayDialog;
import com.example.xgj.livemi.view.weight.PlayAnchorInfoDialog;
import com.example.xgj.livemi.view.weight.PlayGiftDialog;
import com.example.xgj.livemi.view.weight.PlayInputDialog;
import com.example.xgj.livemi.view.weight.PrivatePlayDialog;

import java.util.ArrayList;
import java.util.List;

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
    ImageView ivPrivateLive;//私播
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
    private PlayAnchorInfoDialog playAnchorInfo;
    private PrivatePlayDialog privatePlayDialog;
    private PlayInputDialog playInputDialog;
    private PlayGiftDialog playGiftDialog;
    private PayDialog payDialog;
    private android.support.v4.app.FragmentManager fragmentManager;


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
        playAnchorInfo = new PlayAnchorInfoDialog();
        privatePlayDialog = new PrivatePlayDialog();
        playInputDialog = new PlayInputDialog();
        payDialog = new PayDialog();
        fragmentManager = getSupportFragmentManager();

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
            , R.id.iv_hide_input, R.id.tv_send, R.id.iv_isShow_tanmu, R.id.et_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_anchorInfo:
                initAnthorDialog();
                break;
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_gift:

                addDatas();
                initPlayGiftDialog();

                break;
            case R.id.iv_sendmessage:
                if (rl_message_input.getVisibility() == View.GONE) {
                    rl_message_input.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.iv_share:
                MyApp.getmMyApp().initShareSdk();
                break;
            case R.id.iv_privateLive:
                initPrivatePlay();
                break;
            case R.id.iv_zan:
                bubblingView.addBubblingItem(images[i++ % 5]);
                break;
            case R.id.iv_hide_input:
                rl_message_input.setVisibility(View.GONE);
                break;
            case R.id.tv_send:
                //存在一个判断，当前用户是否够资格去发送评论
                iniPlayInput();//当不够资格是弹出这个弹窗

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
            case R.id.et_message:
                et_message.requestFocus();
                InputMethodManager systemService = (InputMethodManager) et_message.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                systemService.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                break;
        }
    }


    /**
     * 初始化私播dialog
     */
    public void initPrivatePlay() {
        privatePlayDialog.show(fragmentManager, "iv_privateLive");
        privatePlayDialog.setPriPlayCallBack(new PrivatePlayDialog.PriPlayCallBack() {
            @Override
            public void Dredge() {
                initPayDialog();
            }
        });
    }

    /**
     * 支付dialog
     */
    private void initPayDialog() {
        payDialog.show(fragmentManager,"iv_privateLive");
        payDialog.setPayDialogCallBack(new PayDialog.PayDialogCallBack() {
            @Override
            public void payWeChat() {

            }

            @Override
            public void payAli() {

            }
        });
    }


    /**
     * 初始化主播信息dialog
     */
    private void initAnthorDialog() {

        playAnchorInfo.show(fragmentManager, "iv_anchorInfo");
        playAnchorInfo.setOnListenCallBcak(new PlayAnchorInfoDialog.OnListenCallBcak() {
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

    /**
     * 初始化发送弹幕dialog，当等级不够时弹出该提示
     */
    private void iniPlayInput() {
        playInputDialog.show(fragmentManager, "tv_send");
    }

    /**
     * 初始化礼物dialog，
     */
    private void initPlayGiftDialog() {
        playGiftDialog.show(fragmentManager, "iv_gift");
        playGiftDialog.setPlayGiftDialogCallBack(new PlayGiftDialog.PlayGiftDialogCallBack() {
            @Override
            public void rechargeMoney() {
                MyAccountActivity.startToActivity(PlayActivity.this);
            }
        });
    }


    private List<PlayGiftEntity> playGiftEntityList;

    //添加数据
    private void addDatas() {
        //        if (playGiftEntityList == null) {
        playGiftEntityList = new ArrayList<>();
        //        }
        for (int i = 0; i < 20; i++) {
            PlayGiftEntity playGiftEntity = new PlayGiftEntity();
            playGiftEntity.setImageUrl("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=638821036,169960006&fm=23&gp=0.jpg");
            playGiftEntity.setPrice("1" + i);
            playGiftEntity.setShopName("哇哈哈" + i + "啦啦啦");
            playGiftEntityList.add(playGiftEntity);
        }
        Log.d("11", "addDatas: " + playGiftEntityList.size());
        playGiftDialog = new PlayGiftDialog(playGiftEntityList);
    }


}
