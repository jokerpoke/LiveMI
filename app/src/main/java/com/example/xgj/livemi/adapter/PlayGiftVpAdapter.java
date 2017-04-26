package com.example.xgj.livemi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.xgj.livemi.view.BaseFragment;

import java.util.List;

/**
 * Created by chen on 2017/4/26.
 */

public class PlayGiftVpAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragmentList;

    public PlayGiftVpAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    //    public PlayGiftVpAdapter(FragmentManager fm) {
    //        super(fm);
    //    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size() ;
    }
}
