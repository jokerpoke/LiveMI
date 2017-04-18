package com.example.xgj.livemi.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowHouseFragment extends BaseFragment {


    public ShowHouseFragment() {
        // Required empty public constructor
    }

    public static ShowHouseFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ShowHouseFragment fragment = new ShowHouseFragment();
        fragment.setArguments(args);
        return fragment;
    }


    //    @Override
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                             Bundle savedInstanceState) {
    //        // Inflate the layout for this fragment
    //        return inflater.inflate(R.layout.fragment_show_house, container, false);
    //    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_show_house;
    }

}
