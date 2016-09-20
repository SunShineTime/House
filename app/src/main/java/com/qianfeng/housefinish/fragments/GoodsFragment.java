package com.qianfeng.housefinish.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.housefinish.R;

/**
 * Created by Administrator on 16-9-19.
 */
public class GoodsFragment extends BaseFragment {

    public static final String TAG = GoodsFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_goods,container,false);
        return layout;
    }
}
