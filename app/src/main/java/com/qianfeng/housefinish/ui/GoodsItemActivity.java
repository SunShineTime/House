package com.qianfeng.housefinish.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.GoodsItemAdapter;
import com.qianfeng.housefinish.http.HttpRequest;
import com.qianfeng.housefinish.model.BigBigGoodsItem;
import com.qianfeng.housefinish.model.BigGoodsItem;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class GoodsItemActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, View.OnClickListener {

    private static final String TAG = GoodsItemActivity.class.getSimpleName();
    public Intent intent;
    public String code;
    public PullToRefreshListView mPull;
    public ListView mListView;
    public GoodsItemAdapter adapter;
    public BigBigGoodsItem list;
    private int page=1;
    public TextView mTitle;

    public TextView mContent;
    public TextView mBack;
    public FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_item);
        initView();
        setupData(State.DOWN);

    }
    private void initView() {
        mBack = (TextView) findViewById(R.id.goods_item_back);
        mBack.setOnClickListener(this);
        mFab = (FloatingActionButton) findViewById(R.id.goods_fab);
        mFab.setOnClickListener(this);
        intent = getIntent();
        code = intent.getStringExtra("code");
//        Log.e(TAG, "initView: "+code );
        mPull = (PullToRefreshListView) findViewById(R.id.goods_item_listview);
        //设置监听
        mPull.setOnRefreshListener(this);
        mListView = mPull.getRefreshableView();
        mPull.setMode(PullToRefreshBase.Mode.BOTH);

        //创建适配器
        adapter = new GoodsItemAdapter(this,null);
        mListView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goods_item_back:
                finish();
                break;
            case R.id.goods_fab:
                Intent intent = new Intent(this, GoodsEnterActivity.class);
                startActivity(intent);
                break;
        }
    }

    enum State{
        DOWN,UP
    }
    private void setupData(final State state) {
        RequestParams requestParams = new RequestParams(HttpRequest.GOODSITEM1+code+"&pageNo="+page+HttpRequest.GOODSITEM2);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                Gson gson=new Gson();
                list = gson.fromJson(result, BigBigGoodsItem.class);
                Log.e(TAG, "initView: ---------"+ list.getData().getActivityPO().getActivityName());
                Log.e(TAG, "onSuccess: --"+list.getData().getActivityPO().getDescription() );
                //实例化
                mTitle = (TextView) findViewById(R.id.goods_item_title);
                //设置标题数据
                mTitle.setText(list.getData().getActivityPO().getActivityName());
                mContent = ((TextView)findViewById(R.id.goods_item_content));
                mContent.setText(list.getData().getActivityPO().getDescription());

                switch (state) {
                    case DOWN:
                        adapter.upData(list.getData().getPagePO().getItems());
                        break;
                    case UP:
                       adapter.addRes(list.getData().getPagePO().getItems());
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );
               mPull.onRefreshComplete();
            }
        });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
     page=1;
        setupData(State.DOWN);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
page++;
        setupData(State.UP);
    }
}
