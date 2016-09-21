package com.qianfeng.housefinish.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.qianfeng.housefinish.R;

/**
 * Created by 徐余璟 on 2016/9/21.
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }
    /**
     * 获取刷新滚动的方向
     * @return
     */
    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context, attrs);
        recyclerView.setId(R.id.recycler);
        return recyclerView;
    }
    /**
     * 是否准备好 下拉刷新
     * @return true 准备好了， false没有准备好
     */
    @Override
    protected boolean isReadyForPullEnd() {
        RecyclerView refreshableView = getRefreshableView();
        View child = refreshableView.getChildAt(refreshableView.getChildCount() - 1);
        //获取Recycler高度
        int height = refreshableView.getHeight();
        int paddingBottom = refreshableView.getPaddingBottom();
        MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
        int bottomMargin = params.bottomMargin;
        int bottom = child.getBottom();

        // 添加一个判断条件 获取一下RecyclerView的Adapter中的item条数
        int itemCount = refreshableView.getAdapter().getItemCount();
        // 计算最后一个view在适配器中的位置
        int childAdapterPosition = refreshableView.getChildAdapterPosition(child);
        return height==paddingBottom+bottomMargin+bottom&&itemCount==childAdapterPosition+1;
    }


    /**
     * 是否准备好上拉加载 （从结束的地方刷新）
     * @return
     */
    @Override
    protected boolean isReadyForPullStart() {
        RecyclerView refreshableView = getRefreshableView();
        View child = refreshableView.getChildAt(refreshableView.getChildCount() - 1);

        //获取RecyclerView高度
        int height = refreshableView.getHeight();
        //获取RecyclerView的底部内边距
        int paddingBottom = refreshableView.getPaddingBottom();
        //获取最后一个item的底部外边距
        MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
        int bottomMargin = params.bottomMargin;
        //获取child底部距离RecyclerView顶部的距离
        int bottom = child.getBottom();

        return height==paddingBottom+bottomMargin+bottom;
    }
}
