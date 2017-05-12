package com.example.xgj.livemi.view.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.transition.Explode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.alibaba.view.BubblingView;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.entity.PlayGiftEntity;
import com.example.xgj.livemi.utils.LogsUtils;
import com.example.xgj.livemi.utils.MyApp;
import com.example.xgj.livemi.utils.SharedPreferencesUtils;
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


    private String videoPath = "http://biggame1.b0.upaiyun.com/video/a9d366a3c18911e631bf9327d458feb9.mp4";
    private boolean fullscreen = false;//全屏/窗口播放切换标志

    private boolean ishideTanMu = true;

    private static String TAG = "HH";


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
    private int videoCurTime;


    @SuppressLint("NewApi")
    @Override
    protected Object getLayoutResIdOrView() {
        getWindow().setEnterTransition(new Explode().setDuration(2000));
        getWindow().setExitTransition(new Explode().setDuration(2000));
        return R.layout.activity_play;
    }

    @Override
    protected String initTitle() {
        return null;
    }

    @Override
    protected void onActivityPrepared() {
        vvMedia.setVideoURI(Uri.parse(videoPath));
        vvMedia.setMediaController(new MediaController(this));
        vvMedia.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                if (mp.isPlaying()) {
                    flBg.setVisibility(View.GONE);
                    LogsUtils.d("222", "onPrepared===" + videoCurTime);
                    if ((videoCurTime) > 1000) {//保存的时长大于一秒
                        vvMedia.seekTo(videoCurTime);
                    }
                }

                int duration = mp.getDuration();
                SharedPreferencesUtils.put(PlayActivity.this, "videoCountTime", duration);


                //                Log.d(TAG, "onPrepared: 视频总时间====" + duration);
                mp.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer mp) {
                        vvMedia.start();
                        mp.start();
                    }
                });

                //缓冲的监听
                //                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                //
                //                    @Override
                //                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                //                        duration00 = vvMedia.getDuration();
                //                        currentPosition00 = vvMedia.getCurrentPosition();
                //                    }
                //                });
            }
        });

        //播放完成的监听
        vvMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                PlayOverActivity.startToActivity(PlayActivity.this);
            }
        });
        //
        //拦截弹出的错误提示
        vvMedia.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(PlayActivity.this);
                dialog.setCancelable(false);
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {//让返回键消失，不取消dialog
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                dialog.setTitle("提示");
                dialog.setMessage("网络状况出现异常，请重新进入房间！");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PlayActivity.startToActivity(PlayActivity.this);
                        dialog.dismiss();
                    }
                });
                dialog.create().show();
                ShowToastUtils.showToast(PlayActivity.this, "直播间出现异常请重新进入!");
                return true;//如果设置true就可以防止他弹出错误的提示框！
            }
        });

    }

    @Override
    protected void initIntentData() {

    }

    @Override
    protected void initView() {
        initVideoParemt();

        playAnchorInfo = new PlayAnchorInfoDialog();
        privatePlayDialog = new PrivatePlayDialog();
        playInputDialog = new PlayInputDialog();
        payDialog = new PayDialog();
        fragmentManager = getSupportFragmentManager();

        //        videoCurTime = (int) SharedPreferencesUtils.get(PlayActivity.this, "videoCurTime", -1);
        //
        //        LogsUtils.d("123","onPrepared=1111"+videoCurTime);
    }

    private void initVideoParemt() {
        //        if(!fullscreen){//设置RelativeLayout的全屏模式
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        vvMedia.setLayoutParams(layoutParams);

        //            fullscreen = true;//改变全屏/窗口的标记
        //        }else{//设置RelativeLayout的窗口模式
        //            RelativeLayout.LayoutParams lp=new  RelativeLayout.LayoutParams(320,240);
        //            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        //            mVideoView01.setLayoutParams(lp);
        //            fullscreen = false;//改变全屏/窗口的标记
    }

    @Override
    protected void initData() {
        //        new Handler().postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                flBg.setVisibility(View.GONE);
        //                rlLive.setVisibility(View.VISIBLE);
        //            }
        //        }, 2000);
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
        payDialog.show(fragmentManager, "iv_privateLive");
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


    @Override
    protected void onPause() {
        super.onPause();
        int currentPosition = vvMedia.getCurrentPosition();
        SharedPreferencesUtils.put(this, "videoCurTime", currentPosition);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        flBg.setVisibility(View.VISIBLE);
        if ((int) SharedPreferencesUtils.get(this, "videoCountTime", -1) > (int) SharedPreferencesUtils.get(this, "videoCurTime", -1)) {
            vvMedia.seekTo((Integer) SharedPreferencesUtils.get(PlayActivity.this, "videoCurTime", -1));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁videoview，释放资源
        if (vvMedia != null && vvMedia.isPlaying()) {
            vvMedia.stopPlayback();
        } else {
            vvMedia.stopPlayback();
        }
    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, PlayActivity.class);
        context.startActivity(intent);
    }


}
