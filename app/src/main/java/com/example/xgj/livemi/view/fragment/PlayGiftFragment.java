package com.example.xgj.livemi.view.fragment;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.entity.PlayGiftEntity;
import com.example.xgj.livemi.utils.GlideUtil;
import com.example.xgj.livemi.utils.ShowToastUtils;
import com.example.xgj.livemi.view.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayGiftFragment extends BaseFragment {


    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    private PlayGiftAdapter playGiftAdapter;
    private List<PlayGiftEntity> playGiftEntityList;


    private PlayGiftFragmentCallBack playGiftFragmentCallBack;

    public void setPlayGiftFragmentCallBack(PlayGiftFragmentCallBack playGiftFragmentCallBack) {
        this.playGiftFragmentCallBack = playGiftFragmentCallBack;
    }

    public PlayGiftFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public PlayGiftFragment(List<PlayGiftEntity> playGiftEntityList) {
        this.playGiftEntityList = playGiftEntityList;
        Log.d("11", "initView构造 " + playGiftEntityList.size());
    }


    @Override
    protected void initListener() {
        playGiftAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_base:
//                        playGiftFragmentCallBack.changeVPpgae();
                        ShowToastUtils.showToast(getContext(), "啦啦啦"+position+"=");
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new GridLayoutManager(getContext(), 4));
        //        addDatas();
        playGiftAdapter = new PlayGiftAdapter(playGiftEntityList);
        rv_list.setAdapter(playGiftAdapter);

    }

    //
    //    //添加数据
    //    private void addDatas() {
    //        if (playGiftEntityList == null) {
    //            playGiftEntityList = new ArrayList<>();
    //        }
    //        for (int i = 0; i < 20; i++) {
    //            PlayGiftEntity playGiftEntity = new PlayGiftEntity();
    //            playGiftEntity.setImageUrl("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=638821036,169960006&fm=23&gp=0.jpg");
    //            playGiftEntity.setPrice("1" + i);
    //            playGiftEntity.setShopName("哇哈哈" + i + "啦啦啦");
    //            playGiftEntityList.add(playGiftEntity);
    //        }
    //    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_play_gift;
    }

    class PlayGiftAdapter extends BaseQuickAdapter<PlayGiftEntity, BaseViewHolder> {
        public PlayGiftAdapter(List<PlayGiftEntity> data) {
            super(R.layout.item_play_gift, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, PlayGiftEntity playGiftEntity) {
            baseViewHolder.addOnClickListener(R.id.ll_base);
            baseViewHolder.setText(R.id.tv_giftprice, playGiftEntity.getPrice());
            baseViewHolder.setText(R.id.tv_giftname, playGiftEntity.getPrice());
            ImageView imageView = baseViewHolder.getView(R.id.iv_img);
            GlideUtil.loadCommon(getContext(), playGiftEntity.getImageUrl(), imageView);
        }
    }

    public interface PlayGiftFragmentCallBack{
        void changeVPpgae();
    }


}
