package com.alibaba.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;


/**
 * Created by Liujianghao on 2015/12/28.
 */
class AnimationViewWrapper {
    private Rect mArea;
    private BubblingImageView mView;
    private ViewGroup mParent;

    public AnimationViewWrapper(Rect area, BubblingImageView view, ViewGroup parent) {
        this.mArea = area;
        this.mView = view;
        this.mParent = parent;
    }

    public void startAnimation(boolean enableScale) {
        PathEvaluator evaluator = new PathEvaluator();
        AnimatorPath path = new AnimatorPath();
        path.moveTo(mArea.left + mArea.width() / 2, mArea.bottom - 50);
        path.curveTo(mArea.left + mArea.width() / 4+(int) (Math.random() * mArea.width()/2), mArea.top + mArea.height() / 2,
                mArea.left + mArea.width() / 4+(int) (Math.random() * mArea.width()/2), mArea.top + mArea.height() / 2,
                mArea.left + (int) (mArea.width() / 2), mArea.top);
        Object[] array = new PathPoint[path.getPoints().size()];
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mView, "alpha", 1.0f, .0f);
        int alphaDuration = 1800 + (int) (Math.random() * 1200);
        int pathDuration = 3800 + (int) (Math.random() * 2200);
        int count = mParent.getChildCount();
        if(count >= 20) {
            count = 20;
        }
        pathDuration += (3000-1000.0f*count/5);
        alphaAnimator.setDuration(alphaDuration)
                .setInterpolator(new AccelerateInterpolator(2));
        array = path.getPoints().toArray(array);
        ObjectAnimator pathAnimator = ObjectAnimator.ofObject(mView, "location", evaluator, array);
        pathAnimator.setDuration(pathDuration)
                .setInterpolator(new DecelerateInterpolator(1));
        if (enableScale) {
            PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", .5f, 1f);
            PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", .5f, 1f);
            ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(mView
                    , scaleXHolder
                    , scaleYHolder);
            int scaleDuration = 1000;
            scaleAnimator.setDuration(scaleDuration).setInterpolator(new OvershootInterpolator(5));
            scaleAnimator.start();
        }


        alphaAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mParent.addView(mView);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mParent.removeView(mView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mParent.removeView(mView);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        pathAnimator.start();
        alphaAnimator.start();
    }
}
