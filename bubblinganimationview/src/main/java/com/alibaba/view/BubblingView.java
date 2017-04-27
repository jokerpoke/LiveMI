package com.alibaba.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.alibaba.view.Bubbling.R;

/**
 * Created by liujianghao on 16-7-7.
 */
public class BubblingView extends FrameLayout {
    public static final int OVER_MODE_DISCARD = 1;
    public static final int OVER_MODE_DELAY = 1 << 1;

    private static final int DEFAULT_DELAY = 500;

    private static final int DEFAULT_MAX_COUNT = 100;

    private static final int ALLOW_MAX_CHILD_COUNT = 400;
    private int mDelay;
    private int mMaxChildCount;
    private int mOverMode;


    public BubblingView(Context context) {
        super(context);
    }

    public BubblingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray;
        typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.AlivcBubblingView,
                0, 0);
        try {
            mMaxChildCount = typedArray.getInt(R.styleable.AlivcBubblingView_max_child_count, DEFAULT_MAX_COUNT);
            mOverMode |= typedArray.getInt(R.styleable.AlivcBubblingView_over_mode, OVER_MODE_DISCARD);
            mDelay = typedArray.getInt(R.styleable.AlivcBubblingView_delay, DEFAULT_DELAY);
        } finally {
            typedArray.recycle();
        }
    }

    public BubblingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray;
        typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.AlivcBubblingView,
                defStyleAttr, 0);
        try {
            mMaxChildCount = typedArray.getInt(R.styleable.AlivcBubblingView_max_child_count, DEFAULT_MAX_COUNT);
            mOverMode |= typedArray.getInt(R.styleable.AlivcBubblingView_over_mode, OVER_MODE_DISCARD);
            mDelay = typedArray.getInt(R.styleable.AlivcBubblingView_delay, DEFAULT_DELAY);
        } finally {
            typedArray.recycle();
        }
    }

    public void addBubblingItem(final Drawable drawable) {
        if (getChildCount() < mMaxChildCount) {
            Message msg = Message.obtain(mHandler, new Runnable() {
                @Override
                public void run() {
                    BubblingImageView imageView = new BubblingImageView(getContext());
                    imageView.setImageDrawable(drawable);
                    imageView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    Rect rect = new Rect();
                    getDrawingRect(rect);
                    AnimationViewWrapper wrapper = new AnimationViewWrapper(rect, imageView, BubblingView.this);
                    wrapper.startAnimation(true);
                }
            });
            mHandler.sendMessage(msg);
        }else if((mOverMode & OVER_MODE_DELAY) != 0) {
                Message msg = Message.obtain(mHandler, new Runnable() {
                    @Override
                    public void run() {
                        addBubblingItem(drawable);
                    }
                });
            mHandler.sendMessageDelayed(msg, mDelay);
        }
    }

    public void addBubblingItem(final BubblingImageView imageView) {
        if (getChildCount() < mMaxChildCount) {
            Message msg = Message.obtain(mHandler, new Runnable() {
                @Override
                public void run() {
                    Rect rect = new Rect();
                    getDrawingRect(rect);
                    AnimationViewWrapper wrapper = new AnimationViewWrapper(rect, imageView, BubblingView.this);
                    wrapper.startAnimation(true);
                }
            });
            mHandler.sendMessage(msg);
        }else if((mOverMode & OVER_MODE_DELAY) != 0) {
            Message msg = Message.obtain(mHandler, new Runnable() {
                @Override
                public void run() {
                    addBubblingItem(imageView);
                }
            });
            mHandler.sendMessageDelayed(msg, mDelay);
        }
    }


    public void addBubblingItem(int resID) {
        Drawable drawable = getResources().getDrawable(resID);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        addBubblingItem(drawable);
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    public void setMaxChildCount(int maxChildCount) {
        if (maxChildCount > ALLOW_MAX_CHILD_COUNT) {
            Log.e("BubblingView", "max child count over the allowed value");
            mMaxChildCount = ALLOW_MAX_CHILD_COUNT;
        } else if (maxChildCount > 0) {
            mMaxChildCount = maxChildCount;
        } else {
            mMaxChildCount = 1;
        }
    }

    public void setOverMode(int value) {
        if((mOverMode & value) == 0) {
            mOverMode |= value;
        }
    }


}
