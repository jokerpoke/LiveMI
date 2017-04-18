package com.example.xgj.livemi.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.entity.NewestEntity;
import com.example.xgj.livemi.utils.GlideUtil;

import java.util.List;

/**
 * Created by chen on 2017/4/17.
 */

public class NewestFragmentAdapter extends BaseQuickAdapter<NewestEntity, BaseViewHolder> {

    private Context mContext;

    public NewestFragmentAdapter(List<NewestEntity> data, Context mContext) {
        super(R.layout.item_newest, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewestEntity s) {
        baseViewHolder.addOnClickListener(R.id.rl_base).addOnClickListener(R.id.tv_status);
        baseViewHolder.setText(R.id.iv_level, s.getLevel());
        baseViewHolder.setText(R.id.tv_status, s.getStatus()).setBackgroundRes(R.id.tv_status, R.drawable.radius_red_bg);
        baseViewHolder.setText((R.id.tv_livenum), s.getTv_livenum() + "人观看");
        ImageView img = baseViewHolder.getView(R.id.iv_live);
        GlideUtil.loadLocal(mContext, R.mipmap.ic_launcher, img);
    }


}
