package com.qianfeng.housefinish.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.ChildAdapter;
import com.qianfeng.housefinish.http.HttpRequest;
import com.qianfeng.housefinish.model.BigProudList;
import com.qianfeng.housefinish.model.Proud;
import com.qianfeng.housefinish.model.ProudList;
import com.qianfeng.housefinish.utils.PullToRefreshRecyclerView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class ChildActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2{
    private String categoryId;
    private PullToRefreshRecyclerView mRefresh;
    private RecyclerView mRecycler;
    private ChildAdapter childAdapter;
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        initView();
        initData(State.DOWN);
    }



    private void initView() {
        mRefresh = (PullToRefreshRecyclerView) findViewById(R.id.child_refresh);
        mRefresh.setOnRefreshListener(this);
        mRecycler = mRefresh.getRefreshableView();
        GridLayoutManager layout = new GridLayoutManager(this,2);
        mRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mRecycler.setLayoutManager(layout);
        childAdapter = new ChildAdapter(this, null);
        mRecycler.setAdapter(childAdapter);
    }

enum State{
        DOWN,UP;
    }
    private void initData(final State state) {
        RequestParams params = new RequestParams(HttpRequest.GOODSCHILD+categoryId+"}&pageNo="+page);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BigProudList bigProudList = gson.fromJson(result, BigProudList.class);
                BigProudList.DataBean data = bigProudList.getData();
                ProudList pagePO = data.getPagePO();
                List<Proud> items = pagePO.getItems();

                switch (state) {
                    case DOWN:
                        childAdapter.updataRes(items);
                        break;
                    case UP:
                        childAdapter.addRes(items);
                        break;
                }



            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
               mRefresh.onRefreshComplete();
            }
        });
    }

    /**
     * 下拉刷新
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        initData(State.DOWN);
    }

    /**
     * 上拉加载
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        initData(State.UP);
    }
}
