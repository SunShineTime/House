package com.qianfeng.housefinish.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
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
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * 软装图库页面
 */
@ContentView(R.layout.magic_images_activity)
public class MagicImageActivity extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2 {

    private static final String TAG = MagicImageActivity.class.getCanonicalName();
    @ViewInject(R.id.magic_images_back)
    private Button mBack;
    @ViewInject(R.id.magic_images_listview)
    private PullToRefreshListView mRefresh;
    private ListView mListView;
    private MagicImageAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        x.view().inject(this);
        initView();
//        setupView();
    }

    private void initView() {
        mBack.setOnClickListener(this);
        mListView = mRefresh.getRefreshableView();
        mRefresh.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mRefresh.setOnRefreshListener(this);
        mAdapter = new MagicImageAdapter(this, null);
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
