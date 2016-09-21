package com.qianfeng.housefinish.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.http.HttpRequest;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 软装魔法页面
 */

public class MagicOnlineActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.magic_online_back)
    private Button mBack;
    @ViewInject(R.id.magic_online_webview)
    private WebView mWeb;
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
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magic_online_activity);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        mBack.setOnClickListener(this);
        //获取浏览器设置
        WebSettings webSettings = mWeb.getSettings();
        //设置WebView浏览的客户端
        mWeb.setWebViewClient(mClient);
        // WebView的加载进度，接收到的信息都存在WebChromeClient
        mWeb.setWebChromeClient(chromeClient);
        //加载网页
        mWeb.loadUrl(HttpRequest.MAGICONLINE);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
