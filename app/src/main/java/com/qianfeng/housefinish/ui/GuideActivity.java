package com.qianfeng.housefinish.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.GuideAdapter;
import com.qianfeng.housefinish.adapter.PagerAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private int[] images = {
            R.mipmap.icon_welcome_guidance1,
            R.mipmap.icon_welcome_guidance2,
            R.mipmap.icon_welcome_guidance3};
    private List<ImageView> mImageViewList;
    private ImageView iv;
    @ViewInject(R.id.welcome_guide)
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        // 填充ViewPager的数据源
        mImageViewList = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            iv = new ImageView(this);
            iv.setBackgroundResource(images[i]);
            mImageViewList.add(iv);
        }
        mAdapter = new PagerAdapter(mImageViewList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        // 缓存使用记录
        SharedPreferences sdf =getSharedPreferences("first", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sdf.edit();
        editor.putBoolean("isFirst",false);
        editor.commit();
        // 跳转到主页面
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 2) {
            mImageViewList.get(position).setOnClickListener(this);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
