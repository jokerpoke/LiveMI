package com.example.xgj.livemi.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.adapter.NewestFragmentAdapter;
import com.example.xgj.livemi.entity.NewestEntity;
import com.example.xgj.livemi.utils.ShowToastUtils;
import com.example.xgj.livemi.view.BaseFragment;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewestFragment extends BaseFragment {

    @BindView(R.id.roll_view_pager)
    RollPagerView roll_view_pager;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    List<NewestEntity> newestEntityList;
    NewestFragmentAdapter newestFragmentAdapter;

    public NewestFragment() {
        // Required empty public constructor
    }

    public static NewestFragment newInstance() {

        Bundle args = new Bundle();

        NewestFragment fragment = new NewestFragment();
        fragment.setArguments(args);
        return fragment;
    }
    //
    //    @Override
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                             Bundle savedInstanceState) {
    //        // Inflate the layout for this fragment
    //        return inflater.inflate(R.layout.fragment_newest, container, false);
    //    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        newestEntityList = new ArrayList<>();

        for (int i = 0; i < 34; i++) {
            NewestEntity newestEntity = new NewestEntity();
            newestEntity.setImage("");
            newestEntity.setLevel("" + i);
            newestEntity.setStatus("" + i + i);
            newestEntity.setTv_livenum("" + i);
            newestEntityList.add(newestEntity);
        }

        newestFragmentAdapter = new NewestFragmentAdapter(newestEntityList, getContext());
        rv_list.setAdapter(newestFragmentAdapter);
        newestFragmentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                NewestEntity item = (NewestEntity) baseQuickAdapter.getItem(position);
                switch (view.getId()){
                    case R.id.tv_status:
                        ShowToastUtils.showToast(getContext(), "lallal");
                        break;
                    case R.id.rl_base:
                        ShowToastUtils.showToast(getContext(), item.getStatus());
                        break;

                }

            }
        });
    }

    @Override
    protected void initView() {
        initViewpage();
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new GridLayoutManager(getContext(), 2));

    }

    private void initViewpage() {
        //设置播放时间间隔
        roll_view_pager.setPlayDelay(2000);
        //设置透明度
        roll_view_pager.setAnimationDurtion(500);
        //设置适配器
        roll_view_pager.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        roll_view_pager.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.WHITE));
        //        roll_view_pager.setHintView(new TextHintView(getActivity()));
        //mRollViewPager.setHintView(null);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_newest;
    }


    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher_round,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }


}
