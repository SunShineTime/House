package com.qianfeng.housefinish.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.GoodsAdapter;
import com.qianfeng.housefinish.http.HttpRequest;
import com.qianfeng.housefinish.model.BigGoodsList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 商城
 */
public class GoodsFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2{

    public static final String TAG = GoodsFragment.class.getSimpleName();
    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private GoodsAdapter adapter;

    private BigGoodsList list;

    private int page=1;
    private View mHeaderOne;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_goods,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupData(State.DOWN);
    }



    private void initView() {
        //实例化控件
        mPullListView = (PullToRefreshListView) layout.findViewById(R.id.fragment_goods_listview);
        //设置监听
        mPullListView.setOnRefreshListener(this);
        //获取ListView
        mListView = mPullListView.getRefreshableView();
        mPullListView.setMode(PullToRefreshBase.Mode.BOTH);
         //修改下拉加载的View
        ILoadingLayout start = mPullListView.getLoadingLayoutProxy(true, false);

        //创建适配器  绑定
        adapter = new GoodsAdapter(getActivity(),null);
        mListView.setAdapter(adapter);

        //添加头布局
        mHeaderOne = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_goods_listview_headone,null);
        mListView.addHeaderView(mHeaderOne);

    }
    enum State{
        DOWN,UP
    }
    private void setupData(final State state) {
        RequestParams requestParams = new RequestParams(HttpRequest.GOODS1+page+HttpRequest.GOODS2);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                Log.e(TAG, "onSuccess: "+result);
                Gson gson=new Gson();
                list = gson.fromJson(result, BigGoodsList.class);
//                    Log.e(TAG, "onSuccess: "+list.getData().getThemeList() );
                    switch (state) {
                    case  DOWN:
                        adapter.addRes(list.getData().getThemeList());
                        break;
                    case UP:
                        adapter.upData(list.getData().getThemeList());
                        break;
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " );

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );
            }

            @Override
            public void onFinished() {
              mPullListView.onRefreshComplete();
            }
        });
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
     page=1;
        setupData(State.DOWN);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        setupData(State.UP);
    }

}
