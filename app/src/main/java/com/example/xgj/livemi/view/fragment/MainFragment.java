package com.example.xgj.livemi.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.adapter.MainTabAdapter;
import com.example.xgj.livemi.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {


    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tablayoutId)
    TabLayout tablayoutId;
    @BindView(R.id.iv_email)
    ImageView ivEmail;
    @BindView(R.id.vp_id)
    ViewPager vpId;

    AttentionFragment attentionFragment;
    HotFragment hotFragment;
    NewestFragment newestFragment;
    ShowHouseFragment showHouseFragment;
    Unbinder unbinder;
    private List<BaseFragment> baseFragmentList;
    private List<String> titleList;
    MainTabAdapter mainTabAdapter;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //    @Override
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                             Bundle savedInstanceState) {
    //        // Inflate the layout for this fragment
    //        return inflater.inflate(R.layout.fragment_main, container, false);
    //    }

    @Override
    protected void initListener() {
        vpId.setAdapter(mainTabAdapter);
        tablayoutId.setupWithViewPager(vpId);
        vpId.setCurrentItem(2);
    }

    @Override
    protected void initData() {
        mainTabAdapter = new MainTabAdapter(getFragmentManager(), titleList, baseFragmentList);

    }

    private void addFrag() {
        baseFragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        baseFragmentList.add(AttentionFragment.newInstance());
        baseFragmentList.add(HotFragment.newInstance());
        baseFragmentList.add(NewestFragment.newInstance());
        baseFragmentList.add(ShowHouseFragment.newInstance());

        titleList.add("关注");
        titleList.add("热门");
        titleList.add("最新");
        titleList.add("秀场");
    }

    @Override
    protected void initView() {
        addFrag();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }


    @OnClick({R.id.iv_search, R.id.iv_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                break;
            case R.id.iv_email:
                break;
        }
    }
}
