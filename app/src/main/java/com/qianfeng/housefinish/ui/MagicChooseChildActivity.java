package com.qianfeng.housefinish.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.http.HttpRequest;
import com.qianfeng.housefinish.utils.ToastUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 软装魔法页面
 */

public class MagicChooseChildActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MagicChooseChildActivity.class.getSimpleName();
    @ViewInject(R.id.magic_choose_child_back)
    private Button mBack;
    @ViewInject(R.id.magic_choose_child_propressBar)
    private ProgressBar mProgressBar;
    @ViewInject(R.id.magic_choose_child_webview)
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
        setContentView(R.layout.magic_choose_child_activity);
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
        mWeb.setWebViewClient(mClient);
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
//        ToastUtil.toast(""+url);
        mWeb.loadUrl(url);




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
