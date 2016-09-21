package com.qianfeng.housefinish.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.housefinish.CircleIndicator;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.GoodsAdapter;
import com.qianfeng.housefinish.adapter.GoodsPagerAdapter;
import com.qianfeng.housefinish.adapter.PagerAdapter;
import com.qianfeng.housefinish.http.HttpRequest;
import com.qianfeng.housefinish.lunbo.AutoScrollViewPager;
import com.qianfeng.housefinish.model.BigGoodsList;
import com.qianfeng.housefinish.model.BigHeadersList;
import com.qianfeng.housefinish.ui.DayDayAwardActivity;
import com.qianfeng.housefinish.ui.GoodsEditorActivity;
import com.qianfeng.housefinish.ui.GoodsEnterActivity;
import com.qianfeng.housefinish.ui.GoodsRightSecondActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 商城
 */
public class GoodsFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, View.OnClickListener {

    public static final String TAG = GoodsFragment.class.getSimpleName();
    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private GoodsAdapter adapter;

    private BigGoodsList list;

    private int page=1;
    private View mHeaderOne;
    private View mHeaderTwo;
    private AutoScrollViewPager mViewPager;
    private List<ImageView> image;
    private PagerAdapter pagerAdapter;
    private CircleIndicator cirHeader;
    private RequestParams headerParams;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    private ImageView mImage;
    private TextView mTextSearch;
    private LinearLayout mLayoutOne;
    public LinearLayout mLayoutTwo;
    public LinearLayout mLayoutThree;
    public LinearLayout mLayoutFour;
    public ImageView mImageMessage;


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
        initViewPager();
        setupHeader();
    }

    private void setupHeader() {
        headerParams = new RequestParams("http://portal-web.zhaidou.com/index/getBoardContent.action?boardCodes=01,02,03");
        x.http().get(headerParams, new Callback.CommonCallback<String>() {
            private BigHeadersList bigHeadersList;

            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                bigHeadersList = gson.fromJson(result, BigHeadersList.class);
                textView1.setText(bigHeadersList.getData().get(2).getProgramPOList().get(0).getName());
                textView2.setText(bigHeadersList.getData().get(2).getProgramPOList().get(1).getName());
                textView3.setText(bigHeadersList.getData().get(2).getProgramPOList().get(2).getName());
                textView4.setText(bigHeadersList.getData().get(2).getProgramPOList().get(3).getName());
                x.image().bind(imageView1,bigHeadersList.getData().get(2).getProgramPOList().get(0).getPictureUrl());
                x.image().bind(imageView2,bigHeadersList.getData().get(2).getProgramPOList().get(1).getPictureUrl());
                x.image().bind(imageView3,bigHeadersList.getData().get(2).getProgramPOList().get(2).getPictureUrl());
                x.image().bind(imageView4,bigHeadersList.getData().get(2).getProgramPOList().get(3).getPictureUrl());
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

    private void initViewPager() {
        image = new ArrayList<>();
        ImageView imageView1 = new ImageView(getActivity());
        ImageView imageView2 = new ImageView(getActivity());
        ImageView imageView3 = new ImageView(getActivity());
        x.image().bind(imageView1,"http://imgs.zhaidou.com/cms/201603/1458349894438.jpg");
        x.image().bind(imageView2,"http://imgs.zhaidou.com/cms/201603/1458526544504.jpg");
        x.image().bind(imageView3,"http://imgs.zhaidou.com/cms/201603/1458526567734.jpg");
        image.add(imageView1);
        image.add(imageView2);
        image.add(imageView3);
        pagerAdapter = new GoodsPagerAdapter(image);
        mViewPager.setAdapter(pagerAdapter);
        //启动自动滚动
        mViewPager.startAutoScroll();
        //设置滚动时间间隔
        mViewPager.setInterval(3000);
        //设置循环滚动时滑动到从边缘滚动到下一个是否需要动画
        mViewPager.setBorderAnimation(true);
        //是否循环滚动
        mViewPager.setCycle(true);
        //当手指碰到是否停止自动滚动
        mViewPager.setStopScrollWhenTouch(true);
        pagerAdapter.notifyDataSetChanged();
    }
    private void initView() {
        mImage = ((ImageView) layout.findViewById(R.id.fragment_goods_image_right));
        mImage.setOnClickListener(this);
        mTextSearch = ((TextView) layout.findViewById(R.id.fragment_goods_text_search));
        mTextSearch.setOnClickListener(this);
        mImageMessage = (ImageView) layout.findViewById(R.id.fragment_goods_image_left);
        mImageMessage.setOnClickListener(this);
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

        //添加头布局ViewPager
        mHeaderTwo = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_goods_header_viewpagr,null);
        mViewPager = ((AutoScrollViewPager) mHeaderTwo.findViewById(R.id.fragment_goods_viewpager));
//        cirHeader = (CircleIndicator)mHeaderTwo.findViewById(R.id.goods_header_indicator);
//        cirHeader.setViewPager(mViewPager);
        mListView.addHeaderView(mViewPager);

        mHeaderOne = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_goods_listview_headone,null);
        imageView1= ((ImageView) mHeaderOne.findViewById(R.id.fragment_goods_listview_head_one_image1));
        imageView2= ((ImageView) mHeaderOne.findViewById(R.id.fragment_goods_listview_head_one_image2));
        imageView3= ((ImageView) mHeaderOne.findViewById(R.id.fragment_goods_listview_head_one_image3));
        imageView4= ((ImageView) mHeaderOne.findViewById(R.id.fragment_goods_listview_head_one_image4));
        textView1= ((TextView) mHeaderOne.findViewById(R.id.fragment_goods_listview_head_one_text1));
        textView2= ((TextView) mHeaderOne.findViewById(R.id.fragment_goods_listview_head_one_text2));
        textView3= ((TextView) mHeaderOne.findViewById(R.id.fragment_goods_listview_head_one_text3));
        textView4= ((TextView) mHeaderOne.findViewById(R.id.fragment_goods_listview_head_one_text4));
        mListView.addHeaderView(mHeaderOne);

        mLayoutOne = (LinearLayout)layout.findViewById(R.id.layout_one);
        mLayoutOne.setOnClickListener(this);
        mLayoutTwo = ((LinearLayout) layout.findViewById(R.id.layout_two));
        mLayoutTwo.setOnClickListener(this);
        mLayoutThree = ((LinearLayout) layout.findViewById(R.id.layout_three));
        mLayoutThree.setOnClickListener(this);
        mLayoutFour = ((LinearLayout) layout.findViewById(R.id.layout_four));
        mLayoutFour.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_goods_image_right:
                Intent intent = new Intent(getActivity(), GoodsRightSecondActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_goods_text_search:
                Intent intent1 = new Intent(getActivity(), GoodsEditorActivity.class);
                startActivity(intent1);
                break;
            case R.id.fragment_goods_image_left:
                Intent intent3 = new Intent(getActivity(), GoodsEnterActivity.class);
                startActivity(intent3);
                break;
            case R.id.layout_one:

                break;
            case R.id.layout_two:

                break;
            case R.id.layout_three:
                Intent intent2 = new Intent(getActivity(), DayDayAwardActivity.class);
                startActivity(intent2);
                break;
            case R.id.layout_four:

                break;
        }
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
                        adapter.upData(list.getData().getThemeList());
                        break;
                    case UP:
                        adapter.addRes(list.getData().getThemeList());
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
