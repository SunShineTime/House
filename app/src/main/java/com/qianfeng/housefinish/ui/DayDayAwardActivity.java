package com.qianfeng.housefinish.ui;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.http.HttpRequest;

public class DayDayAwardActivity extends BaseActivity implements View.OnClickListener {

    public WebView mWebView;
    public Button mBtn;
    public ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_day_award);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.day_day_webview);
        mBtn = (Button) findViewById(R.id.day_day_image_back);
        mBtn.setOnClickListener(this);
        mProgress = (ProgressBar) findViewById(R.id.day_day_propressBar);
        //获取浏览器设置
        WebSettings webSettings = mWebView.getSettings();
        //设置WebView浏览的客户端
        mWebView.setWebViewClient(mClient);
        // WebView的加载进度，接收到的信息都存在WebChromeClient
        mWebView.setWebChromeClient(chromeClient);
        mWebView.getProgress();
        //加载网页
        mWebView.loadUrl(HttpRequest.DAYDAY);
    }
    //获取WebView 客户端
     private WebViewClient mClient=new WebViewClient();
    // WebChromClient客户端
    private WebChromeClient chromeClient=new WebChromeClient(){
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgress.setProgress(newProgress);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.day_day_image_back:
                finish();
                break;
        }
    }
}
