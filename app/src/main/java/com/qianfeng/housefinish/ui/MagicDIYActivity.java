package com.qianfeng.housefinish.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.qianfeng.housefinish.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * DIY页面
 */

public class MagicDIYActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.magic_diy_back)
    private Button mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magic_diy_activity);
        x.view().inject(this);
        initView();
    }

    private void initView() {

        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
