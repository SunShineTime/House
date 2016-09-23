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
import com.qianfeng.housefinish.adapter.MagicClassroomAdapter;
import com.qianfeng.housefinish.http.HttpRequest;
import com.qianfeng.housefinish.model.BigMagicClassroomList;
import com.qianfeng.housefinish.model.MagicClassroom;
import com.qianfeng.housefinish.model.MagicImageList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class MagicClassroomActivity extends AppCompatActivity implements View.OnClickListener, PullToRefreshListView.OnRefreshListener2 {

    private static final String TAG = MagicClassroomActivity.class.getCanonicalName();
    @ViewInject(R.id.magic_classroom_back)
    private Button mBack;
    @ViewInject(R.id.magic_classroom_listview)
    private PullToRefreshListView mRefresh;
    private ListView mListView;
    private int page = 1;
    private MagicClassroomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_classroom);
        x.view().inject(this);
        initView();
        setupView(State.DOWN);
    }


    private void initView() {
        mBack.setOnClickListener(this);
        mListView = mRefresh.getRefreshableView();
        mRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mRefresh.setOnRefreshListener(this);
        mAdapter = new MagicClassroomAdapter(this, null);
        mListView.setAdapter(mAdapter);
    }

    enum State {
        DOWN, UP
    }

    private void setupView(final State state) {
        RequestParams params = new RequestParams(HttpRequest.MAGICCLASSROOM + page);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " + result);
                Gson gson = new Gson();
                BigMagicClassroomList bigMagicClassroomList = gson.fromJson(result, BigMagicClassroomList.class);
                List<MagicClassroom> list = bigMagicClassroomList.getData().getPostsPOList();
                Log.e(TAG, "onSuccess: " + bigMagicClassroomList);
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
