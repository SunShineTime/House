package com.qianfeng.housefinish.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.adapter.GoodsLeftAdapter;
import com.qianfeng.housefinish.model.BigChildList;
import com.qianfeng.housefinish.model.Child;
import com.qianfeng.housefinish.model.ChildList;
import com.qianfeng.housefinish.model.GoodsLeft;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class GoodsRightSecondActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private RadioGroup mRadioGroup;
    private GridView mGridView;
    private GoodsLeftAdapter adapter;
    private ImageView mBack;
    private TextView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_right_second);

        initView();
        initData(0);

    }



    private void initView() {

        mBack = (ImageView) findViewById(R.id.left_back);
        mBack.setOnClickListener(this);
        mSearch = (TextView) findViewById(R.id.left_search);
        mSearch.setOnClickListener(this);

        mRadioGroup = (RadioGroup) findViewById(R.id.goods_left_rb_radiogroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mGridView = (GridView) findViewById(R.id.goods_left_gridview);
        mGridView.setOnItemClickListener(this);
        adapter = new GoodsLeftAdapter(this,null);
        mGridView.setAdapter(adapter);


    }
    private void initData(final int position) {
        RequestParams params = new RequestParams("http://portal-web.zhaidou.com/category/queryCategory.action");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                GoodsLeft goodsLeft = gson.fromJson(result, GoodsLeft.class);
                BigChildList data = goodsLeft.getData();
                List<ChildList> children = data.getChildren();
                ChildList childList = children.get(position);
                List<Child> list = childList.getChildren();
                adapter.updateRes(list);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.goods_left_rb_jj:
                initData(0);
                break;
            case R.id.goods_left_rb_js:
                initData(1);
                break;
            case R.id.goods_left_rb_jf:
                initData(2);
                break;
            case R.id.goods_left_rb_sn:
                initData(3);
                break;
            case R.id.goods_left_rb_cc:
                initData(4);
                break;
            case R.id.goods_left_rb_ms:
                initData(5);
                break;
            case R.id.goods_left_rb_qj:
                initData(6);
                break;
            case R.id.goods_left_rb_yh:
                initData(7);
                break;
            case R.id.goods_left_rb_sj:
                initData(8);
                break;
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.left_back:
                this.finish();
                break;
            case R.id.left_search:
                Intent intent = new Intent(this, GoodsEditorActivity.class);
                startActivity(intent);
                break;

        }

    }
    //GridView的监听事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String categoryId = adapter.getItem(position).getCategoryId();
        Intent intent = new Intent(this, ChildActivity.class);
        intent.putExtra("categoryId",categoryId);
        startActivity(intent);
    }
}
