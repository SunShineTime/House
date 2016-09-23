package com.qianfeng.housefinish.ui;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qianfeng.housefinish.R;

public class SearchShopActivity extends BaseActivity implements View.OnClickListener {

    public ImageView mSearchBack;
    public ImageView mOrder;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_search_shop);
        initView();
    }

    private void initView() {
        mSearchBack = (ImageView) findViewById(R.id.fragment_goods_search_back);
        mSearchBack.setOnClickListener(this);
        mOrder = (ImageView) findViewById(R.id.fragment_goods_image_order);
        mOrder.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_goods_search_back:
                finish();
                break;
            case R.id.fragment_goods_image_order:
                Toast.makeText(this, "请选择排序", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
