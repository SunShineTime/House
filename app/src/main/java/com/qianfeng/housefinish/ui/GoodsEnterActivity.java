package com.qianfeng.housefinish.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qianfeng.housefinish.R;

public class GoodsEnterActivity extends BaseActivity implements View.OnClickListener {

    public TextView mEnterBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_enter);
        initView();
    }

    private void initView() {

        mEnterBack = (TextView) findViewById(R.id.day_day_enter_back);
        mEnterBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.day_day_enter_back:

                finish();

                break;
        }
    }
}
