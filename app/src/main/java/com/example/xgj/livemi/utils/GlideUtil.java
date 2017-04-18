package com.example.xgj.livemi.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xgj.livemi.R;

/**
 * Created by chen on 2017/4/18.
 */

public class GlideUtil {
    /**
     * 普通加载
     * @param context
     * @param imgurl
     * @param imageView
     */
    public static  void loadCommon(Context context, String imgurl, ImageView imageView){
        Glide.with(context)
                .load(Uri.parse(imgurl))
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.radius_red_bg)
                .into(imageView);
    }

    /**
     * 加载本地
     * @param context
     * @param imgurl
     * @param imageView
     */
    public static  void loadLocal(Context context, Integer imgurl, ImageView imageView){
        Glide.with(context)
                .load(imgurl)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.radius_red_bg)
                .into(imageView);
    }
}