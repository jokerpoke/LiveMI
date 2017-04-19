package com.example.xgj.livemi.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.entity.HotEntity;
import com.example.xgj.livemi.view.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.srlayout)
    SwipeRefreshLayout srlayout;
    HotAdapter hotAdapter;

    public HotFragment() {
        // Required empty public constructor
    }

    public static HotFragment newInstance() {

        Bundle args = new Bundle();

        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }
    //
    //    @Override
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                             Bundle savedInstanceState) {
    //        // Inflate the layout for this fragment
    //        return inflater.inflate(R.layout.fragment_hot, container, false);
    //    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if (hotAdapter==null){
//            hotAdapter=new HotAdapter();
        }
    }

    @Override
    protected void initView() {
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_hot;
    }

    class HotAdapter extends BaseQuickAdapter<HotEntity,BaseViewHolder> {
        public HotAdapter(List<HotEntity> data) {
            super(R.layout.item_hot_frag,data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, HotEntity hotEntity) {

        }
    }

}
