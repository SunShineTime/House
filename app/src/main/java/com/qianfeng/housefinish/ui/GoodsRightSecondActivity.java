package com.qianfeng.housefinish.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.RadioGroup;

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

public class GoodsRightSecondActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private GridView mGridView;
    private GoodsLeftAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_right_second);

        initView();
        initData(0);

    }



    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.goods_left_rb_radiogroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mGridView = (GridView) findViewById(R.id.goods_left_gridview);
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



}
