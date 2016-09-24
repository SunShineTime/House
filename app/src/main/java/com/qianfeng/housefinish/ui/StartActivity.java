package com.qianfeng.housefinish.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianfeng.housefinish.R;


/**
 * 判断开始页面是否为导航页
 */

public class StartActivity extends AppCompatActivity implements Handler.Callback{

    private static final int GO_GUIDE = 100;
    private static final long DELAY_TIME = 2 * 1000;
    private static final int GO_MAIN = 101;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();

    }
    private void initView() {
        // 使用CallBack接口实例化Handler
        mHandler = new Handler(this);
        // 检查是否是第一次使用
        SharedPreferences first = getSharedPreferences("first", MODE_PRIVATE);
        // 获取缓存的标记
        boolean isFirst = first.getBoolean("isFirst", true);
        // 判断标记，进行不同的页面跳转
        if (isFirst) {
            // 跳导航页面
//            goActivity(GuideActivity.class);
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, DELAY_TIME);
        } else {
            // 跳主页面
//            goActivity(MainActivity.class);
            mHandler.sendEmptyMessageDelayed(GO_MAIN, DELAY_TIME);
        }
    }

    private void goActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        // 结束当前页面
        finish();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case GO_GUIDE:
                goActivity(GuideActivity.class);
                break;
            case GO_MAIN:
                goActivity(MainActivity.class);
                break;
        }
        return false;
    }
}
