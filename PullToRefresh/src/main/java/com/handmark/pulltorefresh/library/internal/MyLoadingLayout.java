package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R;

/**
 * loading动画
 */
public class MyLoadingLayout extends LoadingLayout  {

    private Animation loadAnimation;

    public MyLoadingLayout(Context context, Mode mode, Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        // 初始化
        loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.myrefresh);
    }

    @Override
    protected int getDefaultDrawableResId() {
       // 默认图片
        return R.drawable.dialog_loading_icon_small;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }
    //下来刷新
    @Override
    protected void pullToRefreshImpl() {
        mHeaderImage.setVisibility(View.VISIBLE);
        mHeaderImageTwo.setVisibility(View.VISIBLE);
    }
    //正在刷新回调
    @Override
    protected void refreshingImpl() {
        mHeaderImage.setVisibility(View.VISIBLE);
        mHeaderImageTwo.setVisibility(View.VISIBLE);
        mHeaderImage.startAnimation(loadAnimation);
    }
    //释放刷新
    @Override
    protected void releaseToRefreshImpl() {
        mHeaderImage.startAnimation(loadAnimation);
    }
    //重新设置
    @Override
    protected void resetImpl() {
        mHeaderImage.clearAnimation();
        /*mHeaderProgress.setVisibility(View.GONE);*/
        mHeaderImage.setVisibility(View.VISIBLE);
        mHeaderImageTwo.setVisibility(View.VISIBLE);
    }
}
