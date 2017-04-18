package com.example.xgj.livemi.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.xgj.livemi.R;
import com.example.xgj.livemi.view.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends BaseFragment {


    public AttentionFragment() {
        // Required empty public constructor
    }

    public static AttentionFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AttentionFragment fragment = new AttentionFragment();
        fragment.setArguments(args);
        return fragment;
    }


//    @Override
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                             Bundle savedInstanceState) {
    //        // Inflate the layout for this fragment
    //        return inflater.inflate(R.layout.fragment_attention, container, false);
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
        return R.layout.fragment_attention;
    }

}
