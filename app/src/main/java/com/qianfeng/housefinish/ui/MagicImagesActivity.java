package com.qianfeng.housefinish.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.MagicImageAdapter;
import com.qianfeng.housefinish.http.HttpRequest;
import com.qianfeng.housefinish.model.MagicImageList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MagicImagesActivity extends AppCompatActivity implements View.OnClickListener,PullToRefreshListView.OnRefreshListener2 {

    private static final String TAG = MagicImagesActivity.class.getCanonicalName();
    @ViewInject(R.id.magic_images_back)
    private Button mBack;
    @ViewInject(R.id.magic_images_listview)
    private PullToRefreshListView mRefresh;
    private ListView mListView;
    private MagicImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_images);
        x.view().inject(this);
        initView();
        setupView();
    }


    private void initView() {
        mBack.setOnClickListener(this);
        mListView=mRefresh.getRefreshableView();
        mRefresh.setOnRefreshListener(this);
        mAdapter = new MagicImageAdapter(this,null);
        mListView.setAdapter(mAdapter);

    }

    private void setupView() {
        RequestParams params = new RequestParams(HttpRequest.MAGICIMAGE);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " + result);
                Gson gson = new Gson();
                MagicImageList list = gson.fromJson(result, MagicImageList.class);
                Log.e(TAG, "onSuccess: " + list);
                mAdapter.updateRes(list.getData());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

                mRefresh.onRefreshComplete();
            }
        });
    }


    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        setupView();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
