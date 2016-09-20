package com.qianfeng.housefinish.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.qianfeng.housefinish.CircleIndicator;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.HomeAdapter;
import com.qianfeng.housefinish.adapter.PagerAdapter;
import com.qianfeng.housefinish.http.HttpRequest;
import com.qianfeng.housefinish.model.BigHomeList;
import com.qianfeng.housefinish.model.HeaderOne;
import com.qianfeng.housefinish.model.Home;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private ViewPager mViewpage;
    private CircleIndicator mCircleIndicator;
    private List<View> viewList;

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



        //添加header1
        View header2 = LayoutInflater.from(getActivity()).inflate(R.layout.header_two, null);
        initPageData();
        mViewpage = ((ViewPager) header2.findViewById(R.id.header_viewpager));
        PagerAdapter adapter = new PagerAdapter(viewList);
        mViewpage.setAdapter(adapter);
        mCircleIndicator = ((CircleIndicator) header2.findViewById(R.id.header_indicator));
        mCircleIndicator.setViewPager(mViewpage);

        mListView.addHeaderView(header2);


        //添加header2
        View header1 = LayoutInflater.from(getActivity()).inflate(R.layout.header_one, null);
        mHeaderImage1 = ((ImageView) header1.findViewById(R.id.header_image1));
        mHeaderImage2 = ((ImageView) header1.findViewById(R.id.header_image2));
        mHeaderImage3 = ((ImageView) header1.findViewById(R.id.header_image3));
        initHeaderDate();
        mListView.addHeaderView(header1);


        this.adapter = new HomeAdapter(getActivity(), null);
        mListView.setAdapter(this.adapter);



    }

    private void initPageData() {
        viewList = new ArrayList<View>();
        Random random = new Random();
        for(int i=0;i<3;i++){
            View view = new View(getActivity());
            view.setBackgroundColor(0xff000000| random.nextInt(0x00ffffff));
            viewList.add(view);
        }
    }

    //header数据加载
    private void initHeaderDate() {
        RequestParams params = new RequestParams(HttpRequest.HOMEHEADERONEURL);
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

    private void initDate() {
        RequestParams params = new RequestParams(HttpRequest.HOMELISTURL);
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
