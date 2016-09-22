package com.qianfeng.housefinish.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.qianfeng.housefinish.ui.GoodsEnterActivity;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements Handler.Callback, View.OnClickListener {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private ListView mListView;
    private HomeAdapter adapter;
    private ImageView mHeaderImage1;
    private ImageView mHeaderImage2;
    private ImageView mHeaderImage3;
    private ViewPager mViewpage;
    private CircleIndicator mCircleIndicator;
    private List<ImageView> viewList;
    private Handler mhandler = new Handler(this);
    private int page = 0 ;
    private DbManager mDb;
    //缓存管理器
    protected DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("zhaidou").setDbVersion(1);
    public ImageView mHomeEnter;

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

    }

    private void initView() {
        mListView = (ListView) layout.findViewById(R.id.homefragment_listview);
        mHomeEnter = (ImageView) layout.findViewById(R.id.home_enter);
        mHomeEnter.setOnClickListener(this);

        //添加header ViewPage
        View header2 = LayoutInflater.from(getActivity()).inflate(R.layout.header_two, null);
        initPageData();
        mViewpage = ((ViewPager) header2.findViewById(R.id.header_viewpager));
        PagerAdapter adapter = new PagerAdapter(viewList);
        mViewpage.setAdapter(adapter);
        mhandler.sendEmptyMessage(100);
        mCircleIndicator = ((CircleIndicator) header2.findViewById(R.id.header_indicator));
        mCircleIndicator.setViewPager(mViewpage);
        mListView.addHeaderView(header2);


        //添加header 三张图片
        View header1 = LayoutInflater.from(getActivity()).inflate(R.layout.header_one, null);
        mHeaderImage1 = ((ImageView) header1.findViewById(R.id.header_image1));
        mHeaderImage2 = ((ImageView) header1.findViewById(R.id.header_image2));
        mHeaderImage3 = ((ImageView) header1.findViewById(R.id.header_image3));
        initHeaderDate();
        mListView.addHeaderView(header1);


        this.adapter = new HomeAdapter(getActivity(), null);
        mListView.setAdapter(this.adapter);
        setUpView();

    }

    private void setUpView() {
        mDb = x.getDb(daoConfig);
        try {
            List<Home> homes = mDb.selector(Home.class).orderBy("id", true).findAll();
            if (homes!=null&&homes.size()!=0) {
                Log.e(TAG, "setUpView: 从数据库中加载" );
                adapter.updateRes(homes);
            }else {
                initDate();
            }
            List<HeaderOne.DataBean.ProgramPOListBean> been = mDb.selector(HeaderOne.DataBean.ProgramPOListBean.class).orderBy("id", true).findAll();
            if (been!=null&&been.size()!=0) {
                x.image().bind(mHeaderImage1,been.get(0).getPictureUrl());
                x.image().bind(mHeaderImage2,been.get(1).getPictureUrl());
                x.image().bind(mHeaderImage3,been.get(2).getPictureUrl());
            }else {
                initHeaderDate();
            }


        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    private void initPageData() {
        viewList = new ArrayList<ImageView>();
        ImageView imageView1 = new ImageView(getActivity());
        ImageView imageView2 = new ImageView(getActivity());
        ImageView imageView3 = new ImageView(getActivity());
        x.image().bind(imageView1,"http://imgs.zhaidou.com/cms/201608/1470736393135.jpg");
        x.image().bind(imageView2,"http://imgs.zhaidou.com/cms/201608/1472628961778.jpg");
        x.image().bind(imageView3,"http://imgs.zhaidou.com/cms/201606/1466481215467.jpg");
        viewList.add(imageView1);
        viewList.add(imageView2);
        viewList.add(imageView3);
    }

    //header (三张图片)数据加载
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
                mDb = x.getDb(daoConfig);
                for (HeaderOne.DataBean.ProgramPOListBean bean :programPOList ) {
                    try {
                        mDb.saveOrUpdate(bean);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }

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
                Log.e(TAG, "onSuccess:从网络请求 " );
                mDb = x.getDb(daoConfig);
                for (Home home : list) {
                    try {
                        mDb.saveOrUpdate(home);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
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
                Log.e(TAG, "onFinished: " );
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case 100:
                page++;
                Log.e(TAG, "handleMessage: "+page );
                mViewpage.setCurrentItem(page%3);
                mhandler.sendEmptyMessageDelayed(100,3*1000);

                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_enter:
                Intent intent = new Intent(getActivity(), GoodsEnterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
