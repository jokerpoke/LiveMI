package com.example.xgj.livemi.textpack;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.entity.PlayGiftEntity;
import com.example.xgj.livemi.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.srl_layout)
    SwipeRefreshLayout srl_layout;

    private List<PlayGiftEntity> playGiftEntityList;
    private InnAdapter innAdapter;
    private PlayGiftEntity playGiftEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {
        srl_layout.setOnRefreshListener(this);
        srl_layout.setColorSchemeColors(Color.rgb(47, 223, 189));


        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        srl_layout.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        srl_layout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        srl_layout.setSize(SwipeRefreshLayout.DEFAULT);


        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        innAdapter = new InnAdapter(playGiftEntityList);

        innAdapter.setOnLoadMoreListener(this, rv_list);
        innAdapter.openLoadAnimation(BaseMultiItemQuickAdapter.SLIDEIN_LEFT);
        rv_list.setAdapter(innAdapter);
    }

    private void initData() {
        playGiftEntityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            playGiftEntity = new PlayGiftEntity();
            playGiftEntity.setPrice("价格是" + i);
            playGiftEntity.setShopName("商品名字是" + i + "=z哈哈");
            playGiftEntity.setImageUrl("http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg");
            if (i % 2 == 0) {
                playGiftEntity.setItemType(200);

            } else
                playGiftEntity.setItemType(100);
            {
                playGiftEntityList.add(playGiftEntity);
            }
        }
    }

    @Override
    public void onRefresh() {
        //        playGiftEntityList.clear();
        //        playGiftEntityList.remove(playGiftEntity);
        //        innAdapter.setNewData(new ArrayList<PlayGiftEntity>());
        new Handler().postAtTime(new Runnable() {
            @Override
            public void run() {
                innAdapter.setNewData(playGiftEntityList);
                innAdapter.notifyDataSetChanged();
                srl_layout.setRefreshing(false);
                innAdapter.setEnableLoadMore(true);
            }
        }, 2000);


    }

    @Override
    public void onLoadMoreRequested() {
//        innAdapter.setNewData();
//        innAdapter.addData();
//        srl_layout.setEnabled(true);


    }

    class InnAdapter extends BaseMultiItemQuickAdapter<PlayGiftEntity, BaseViewHolder> {
        public InnAdapter(List<PlayGiftEntity> data) {
            super(data);
            addItemType(PlayGiftEntity.TYPE_1, R.layout.main2_item_layout1);
            addItemType(PlayGiftEntity.TYPE_2, R.layout.main2_item_layout002);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, PlayGiftEntity playGiftEntity) {
            switch (baseViewHolder.getItemViewType()) {
                case PlayGiftEntity.TYPE_1:
                    baseViewHolder.setText(R.id.tv_title, playGiftEntity.getShopName());
                    baseViewHolder.setText(R.id.tv_prive, playGiftEntity.getPrice());
                    ImageView iv_img = baseViewHolder.getView(R.id.iv_img);
                    GlideUtil.loadCommon(Main2Activity.this, playGiftEntity.getImageUrl(), iv_img);
                    break;
                case PlayGiftEntity.TYPE_2:
                    baseViewHolder.setText(R.id.tv_title2, playGiftEntity.getShopName());
                    baseViewHolder.setText(R.id.tv_prive2, playGiftEntity.getPrice());
                    ImageView iv_img2 = baseViewHolder.getView(R.id.iv_img2);
                    GlideUtil.loadCommon(Main2Activity.this, playGiftEntity.getImageUrl(), iv_img2);
                    break;
            }

        }
    }
}
