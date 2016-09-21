package com.qianfeng.housefinish.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.utils.PullToRefreshRecyclerView;

public class ChildActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2{
    private String categoryId;
    private PullToRefreshRecyclerView mRefresh;
    private RecyclerView mRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        initView();
    }

    private void initView() {
        mRefresh = (PullToRefreshRecyclerView) findViewById(R.id.child_refresh);
        mRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mRefresh.setOnRefreshListener(this);
        mRecycler = mRefresh.getRefreshableView();
        GridLayoutManager layout = new GridLayoutManager(this,2);
        mRecycler.setLayoutManager(layout);

    }

    /**
     * 下拉刷新
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    /**
     * 上拉加载
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
