package com.qianfeng.housefinish.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.http.HttpRequest;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 软装魔法页面
 */

public class MagicChooseActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MagicChooseActivity.class.getSimpleName();
    @ViewInject(R.id.magic_choose_back)
    private Button mBack;
    @ViewInject(R.id.magic_choose_propressBar)
    private ProgressBar mProgressBar;
    @ViewInject(R.id.magic_choose_webview)
    private WebView mWeb;
    //获取WebView 客户端
    private WebViewClient mClient = new WebViewClient();
    // WebChromClient客户端
    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magic_choose_activity);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        mBack.setOnClickListener(this);
        //获取浏览器设置
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // WebView的加载进度，接收到的信息都存在WebChromeClient
        mWeb.setWebChromeClient(chromeClient);
        mWeb.getProgress();
        //加载网页

        //设置WebView浏览的客户端
        mWeb.setWebViewClient(new WebViewClient(){


            //设置web跳转activity，回退时还在当前的页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals("http://m.zhaidou.com/decorate/guide?source=android")) {
                    //false是默认不拦截当前的web加载，默认模式
                    return false;
                }else {
                    Intent intent = new Intent(getApplication(), MagicChooseChildActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                    //true是拦截当前的web加载
                    return true;
                }

            }
        });
        mWeb.loadUrl(HttpRequest.MAGICCHOOSE);




    }

    @Override
    public void onClick(View v) {
        //判断WebView是否可回退
        if (mWeb.canGoBack()) {
            //进行返回
            mWeb.goBack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断按下的按钮是否是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //判断WebView是否可回退
            if (mWeb.canGoBack()) {
                mWeb.goBack();
                return true;
            } else {
                finish();
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
