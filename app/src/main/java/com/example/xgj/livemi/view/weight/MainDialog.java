package com.example.xgj.livemi.view.weight;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xgj.livemi.R;
import com.example.xgj.livemi.entity.MainDialogEntity;
import com.example.xgj.livemi.utils.ShowToastUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chen on 2017/4/19.
 */

@SuppressLint("ValidFragment")
public class MainDialog extends DialogFragment {
    private View view;
    private RelativeLayout rl_dialog;
    private RecyclerView rv_list;
    private ImageView iv_close;
    private TextView tv_submit;
    private LinearLayout ll_base;
    private MainDialogCakkBcak mainDialogCakkBcak;
    private Context context;

    private List<MainDialogEntity> mainDialogEntityList;
    private MainDialogAdapter mainDialogAdapter;

    /**
     * 传一个数据进来
     *
     * @param
     */
        public MainDialog(Context context) {
            this.context=context;
        }
    public void setMainDialogCakkBcak(MainDialogCakkBcak mainDialogCakkBcak) {
        this.mainDialogCakkBcak = mainDialogCakkBcak;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_DialogWhenLarge_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_main, container);
        rl_dialog = (RelativeLayout) view.findViewById(R.id.rl_dialog);
        ll_base = (LinearLayout) view.findViewById(R.id.ll_base);
        rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
        iv_close = (ImageView) view.findViewById(R.id.iv_close);
        tv_submit = (TextView) view.findViewById(R.id.tv_submit);
        getDialog().getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        initRecycleView();

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ll_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mainDialogCakkBcak.upDatas();
                ShowToastUtils.showToast(context,"hhh");
                dismiss();
            }
        });

        return view;
    }

    private void initRecycleView() {
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new GridLayoutManager(context, 3));

        if (mainDialogEntityList == null) {
            mainDialogEntityList = new ArrayList<>();
        }

        if (mainDialogAdapter == null) {
            mainDialogAdapter = new MainDialogAdapter(mainDialogEntityList);
        }

        rv_list.setAdapter(mainDialogAdapter);
    }

    public interface MainDialogCakkBcak {
        void upDatas();
    }

    class MainDialogAdapter extends BaseQuickAdapter<MainDialogEntity, BaseViewHolder> {
        public MainDialogAdapter(List<MainDialogEntity> data) {
            super(R.layout.item_main_dialog, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, MainDialogEntity mainDialogEntity) {
            baseViewHolder.addOnClickListener(R.id.iv_userImg);
//            baseViewHolder.setText(R.id.tv_name)
            CircleImageView circleImageView=baseViewHolder.getView(R.id.iv_userImg);
//            GlideUtil.loadCommon(context,,circleImageView);

        }

    }
}


