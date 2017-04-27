package com.alibaba.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by liujianghao on 16-7-5.
 */
public class BubblingImageView extends ImageView{
    public BubblingImageView(Context context) {
        super(context);
    }

    public BubblingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubblingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLocation(PathPoint pathPoint) {
        this.setTranslationX(pathPoint.mX);
        this.setTranslationY(pathPoint.mY);
    }

}
