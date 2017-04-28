package com.example.xgj.livemi.view.weight;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.adapter.PlayGiftVpAdapter;
import com.example.xgj.livemi.entity.PlayGiftEntity;
import com.example.xgj.livemi.utils.ShowToastUtils;
import com.example.xgj.livemi.view.BaseFragment;
import com.example.xgj.livemi.view.fragment.PlayGiftFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2017/4/26.
 */

@SuppressLint("ValidFragment")
public class PlayGiftDialog extends DialogFragment {
    private View view;

    private LinearLayout ll_base;
    private ViewPager vpGiftId;
    private LinearLayout llContains;//显示点的容易布局
    private TextView tvText1;//余额文字
    private TextView tvMoney;//余额数量
    private TextView tvRecharge; //充值

    private PlayGiftVpAdapter adapter;
    private List<BaseFragment> listFragment = new ArrayList<>();
    private List<PlayGiftEntity> playGiftEntityList = new ArrayList<>();

    private int currPoint;
    private ImageView imagePoint;
    private List<ImageView> imageList;


    private PlayGiftDialogCallBack playGiftDialogCallBack;


    private PlayGiftFragment playGiftFragment;

    public void setPlayGiftDialogCallBack(PlayGiftDialogCallBack playGiftDialogCallBack) {
        this.playGiftDialogCallBack = playGiftDialogCallBack;
    }


    public PlayGiftDialog(List<PlayGiftEntity> playGiftEntityList) {
        this.playGiftEntityList = playGiftEntityList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_DialogWhenLarge_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.play_gift_dailog, container);
        getDialog().getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        ll_base = (LinearLayout) view.findViewById(R.id.ll_base);
        vpGiftId = (ViewPager) view.findViewById(R.id.vp_giftId);
        llContains = (LinearLayout) view.findViewById(R.id.ll_contains);
        tvText1 = (TextView) view.findViewById(R.id.tv_text1);
        tvMoney = (TextView) view.findViewById(R.id.tv_money);
        tvRecharge = (TextView) view.findViewById(R.id.tv_recharge);

        initFragment();


        tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGiftDialogCallBack.rechargeMoney();
                dismiss();
            }
        });

        ll_base.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int viewY = vpGiftId.getTop();
                int touchY = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (touchY < viewY) {
                        dismiss();
                    }
                }

                return false;
            }
        });
        return view;
    }

    private void initFragment() {
        initFragmentNum();


    }

    private void initFragmentNum() {
        int startIndex = 0;
        int endIndex = 7;
        int pointNum = 0;


        int fragNum = playGiftEntityList.size() / 8;//每页有8个数据的有几页
        int fragMaybeNum = playGiftEntityList.size() % 8;//最后一页有无数据的判断，或者未满8个时
        if (fragNum == 0 || fragMaybeNum == 0) {
            //没数据，没礼物显示
        } else if (fragNum == 0 && fragMaybeNum > 0) {
            //fragment数量为1
            PlayGiftFragment playGiftFragment = new PlayGiftFragment(playGiftEntityList);
            pointNum = 0;
        } else if (fragNum > 0 && fragMaybeNum == 0) {
            //viewpager数量为fragnum
            for (int aa = 0; aa < fragNum; aa++) {
                if (startIndex > playGiftEntityList.size()) {
                    return;
                } else if ((startIndex + 8) > playGiftEntityList.size() && startIndex < playGiftEntityList.size()) {//最后一页数据的处理
                    List<PlayGiftEntity> playGiftEntities = playGiftEntityList.subList(startIndex, playGiftEntityList.size());
                    playGiftFragment = new PlayGiftFragment(playGiftEntities);

                    playGiftFragment.setPlayGiftFragmentCallBack(new PlayGiftFragment.PlayGiftFragmentCallBack() {
                        @Override
                        public void changeVPpgae(int positionPGF) {
                            int realPosition = currPoint * 8 + positionPGF;
                            String imageUrl = playGiftEntityList.get(realPosition).getImageUrl();
                            ShowToastUtils.showToast(getContext(), "kkkkkk===" + "===" + imageUrl);
                        }
                    });

                    listFragment.add(playGiftFragment);

                } else {
                    List<PlayGiftEntity> playGiftEntities = playGiftEntityList.subList(startIndex, startIndex + 8);
                    playGiftFragment = new PlayGiftFragment(playGiftEntities);

                    playGiftFragment.setPlayGiftFragmentCallBack(new PlayGiftFragment.PlayGiftFragmentCallBack() {
                        @Override
                        public void changeVPpgae(int positionPGF) {
                            int realPosition = currPoint * 8 + positionPGF;
                            String imageUrl = playGiftEntityList.get(realPosition).getShopName();
                            ShowToastUtils.showToast(getContext(), "kkkkkk===" + "===" + imageUrl);
                        }
                    });

                    listFragment.add(playGiftFragment);
                    //改变下次的起始位置，可以直接加8
                    startIndex = startIndex + 8;
                }

            }

            pointNum = fragNum;


        } else if (fragNum > 0 && fragMaybeNum > 0)

        {
            //viewpage 数量为fragment+1
            for (int aa = 0; aa < fragNum + 1; aa++) {
                if (startIndex > playGiftEntityList.size()) {
                    return;
                } else if ((startIndex + 8) > playGiftEntityList.size() && startIndex < playGiftEntityList.size()) {//最后一页数据的处理
                    List<PlayGiftEntity> playGiftEntities = playGiftEntityList.subList(startIndex, playGiftEntityList.size());
                    playGiftFragment = new PlayGiftFragment(playGiftEntities);

                    playGiftFragment.setPlayGiftFragmentCallBack(new PlayGiftFragment.PlayGiftFragmentCallBack() {
                        @Override
                        public void changeVPpgae(int positionPGF) {
                            int realPosition = currPoint * 8 + positionPGF;
                            ShowToastUtils.showToast(getContext(), "kkkkkk===" + "===" + playGiftEntityList.get(realPosition).getPrice());
                        }
                    });

                    listFragment.add(playGiftFragment);
                    //
                } else {
                    List<PlayGiftEntity> playGiftEntities = playGiftEntityList.subList(startIndex, startIndex + 8);
                    playGiftFragment = new PlayGiftFragment(playGiftEntities);

                    playGiftFragment.setPlayGiftFragmentCallBack(new PlayGiftFragment.PlayGiftFragmentCallBack() {
                        @Override
                        public void changeVPpgae(int positionPGF) {
                            int realPosition = currPoint * 8 + positionPGF;
                            String imageUrl = playGiftEntityList.get(realPosition).getShopName();
                            ShowToastUtils.showToast(getContext(), "kkkkkk===" + "===" + imageUrl);
                        }
                    });
                    listFragment.add(playGiftFragment);
                    //改变下次的起始位置，可以直接加8
                    startIndex = startIndex + 8;
                }

            }
            pointNum = fragNum + 1;


        }
        adapter = new PlayGiftVpAdapter(getChildFragmentManager(), listFragment);//添加页面
        vpGiftId.setAdapter(adapter);

        //添加viewpager下面的坐标指示点
        imageList = new ArrayList<>();
        for (int x = 0; x < pointNum; x++) {
            imagePoint = new ImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //            imagePoint.setBackgroundColor(Color.parseColor("#D2691E"));//点的起始颜色
            imagePoint.setImageResource(R.drawable.heart0);
            imagePoint.setPadding(5, 5, 5, 5);
            imagePoint.setLayoutParams(lp);
            llContains.addView(imagePoint);
            imageList.add(imagePoint);
        }

        //起始指示点的图片颜色
        imageList.get(0).setImageResource(R.drawable.heart1);

        //viewpage监听，点的当前位置及颜色

        vpGiftId.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                imageList.get(currPoint).setImageResource(R.drawable.heart0);
                imageList.get(position).setImageResource(R.drawable.heart1);
                currPoint = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public interface PlayGiftDialogCallBack {
        void rechargeMoney();

    }
}
