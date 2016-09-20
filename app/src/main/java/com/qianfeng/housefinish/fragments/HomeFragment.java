package com.qianfeng.housefinish.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.HomeAdapter;
import com.qianfeng.housefinish.model.BigHomeList;
import com.qianfeng.housefinish.model.HeaderOne;
import com.qianfeng.housefinish.model.Home;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private ListView mListView;
    private HomeAdapter adapter;
    private ImageView mHeaderImage1;
    private ImageView mHeaderImage2;
    private ImageView mHeaderImage3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_home,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        initDate();
    }

    private void initView() {
        mListView = (ListView) layout.findViewById(R.id.homefragment_listview);

        //添加header
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_one, null);
        mHeaderImage1 = ((ImageView) view.findViewById(R.id.header_image1));
        mHeaderImage2 = ((ImageView) view.findViewById(R.id.header_image2));
        mHeaderImage3 = ((ImageView) view.findViewById(R.id.header_image3));
        initHeaderDate();
        mListView.addHeaderView(view);

        adapter = new HomeAdapter(getActivity(), null);
        mListView.setAdapter(adapter);



    }

    private void initHeaderDate() {
        RequestParams params = new RequestParams("http://portal-web.zhaidou.com/index/getBoardContent.action?boardCodes=06");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HeaderOne headerOne = gson.fromJson(result, HeaderOne.class);
                List<HeaderOne.DataBean.ProgramPOListBean> programPOList = headerOne.getData().get(0).getProgramPOList();
                x.image().bind(mHeaderImage1,programPOList.get(0).getPictureUrl());
                x.image().bind(mHeaderImage2,programPOList.get(1).getPictureUrl());
                x.image().bind(mHeaderImage3,programPOList.get(2).getPictureUrl());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void initDate() {
        RequestParams params = new RequestParams("http://portal-web.zhaidou.com/decorate/getChangeCases.action?pageSize=20&pageNo=1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                Gson gson = new Gson();
                BigHomeList bigHomeList = gson.fromJson(result, BigHomeList.class);
                List<Home> list = bigHomeList.getData().getFreeClassicsCasePOs();
                adapter.updateRes(list);

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
                Log.e(TAG, "onFinished: " );
            }
        });
    }
}
