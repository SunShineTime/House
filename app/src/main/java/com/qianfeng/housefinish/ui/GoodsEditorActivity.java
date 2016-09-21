package com.qianfeng.housefinish.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qianfeng.housefinish.R;

public class GoodsEditorActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_editor);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.fragment_goods_image_back);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_goods_image_back:
               finish();
                break;
        }
    }
}
