package com.qianfeng.housefinish.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.housefinish.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 改造案例页面
 */
@ContentView(R.layout.magic_classic_activity)
public class MagicClassicActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2{

    @ViewInject(R.id.magic_classic_listview)
    private PullToRefreshListView mRefresh;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        //设置刷新、加载的监听
        mRefresh.setOnRefreshListener(this);
        //获取listView
        mListView = mRefresh.getRefreshableView();
        mRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        //设置适配器




    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
