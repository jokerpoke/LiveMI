package com.example.xgj.livemi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.xgj.livemi.view.BaseFragment;

import java.util.List;

/**
 * Created by chen on 2017/4/17.
 */

public class MainTabAdapter extends FragmentPagerAdapter {
    private List<String> mlist;
    private List<BaseFragment> mbaseFGList;

    public MainTabAdapter(FragmentManager fm, List<String> mlist, List<BaseFragment> mbaseFGList) {
        super(fm);
        this.mlist = mlist;
        this.mbaseFGList = mbaseFGList;
    }

    @Override
    public Fragment getItem(int position) {
        return mbaseFGList.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size() == 0 ? 0 : mlist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mlist.get(position);
    }
}
