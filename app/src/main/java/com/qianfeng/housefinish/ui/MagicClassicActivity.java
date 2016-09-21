package com.qianfeng.housefinish.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.HomeAdapter;
import com.qianfeng.housefinish.http.HttpRequest;
import com.qianfeng.housefinish.model.BigHomeList;
import com.qianfeng.housefinish.model.Home;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_magic_classic)
public class MagicClassicActivity extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2 {

    private static final String TAG = MagicClassicActivity.class.getSimpleName();
    @ViewInject(R.id.magic_classic_back)
    private Button mBack;
    @ViewInject(R.id.magic_classic_listview)
    private PullToRefreshListView mRefresh;
    private ListView mListView;
    private HomeAdapter mAdapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        setupView(State.DOWN);
    }

    private void initView() {
        mBack.setOnClickListener(this);
        mListView = mRefresh.getRefreshableView();
        mRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mRefresh.setOnRefreshListener(this);
        mAdapter = new HomeAdapter(this, null);
        mListView.setAdapter(mAdapter);

    }

    enum State {
        DOWN, UP
    }

    private void setupView(final State state) {

        RequestParams params = new RequestParams(HttpRequest.MAGICCLASSIC + page);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " + result);
                Gson gson = new Gson();
                BigHomeList bigHomeList = gson.fromJson(result, BigHomeList.class);
                List<Home> list = bigHomeList.getData().getFreeClassicsCasePOs();
                switch (state) {
                    case DOWN:
                        mAdapter.updateRes(list);
                        break;
                    case UP:
                        mAdapter.addRes(list);
                        break;
                }
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
        page = 1;
        setupView(State.DOWN);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        setupView(State.UP);
    }
}
