package com.qianfeng.housefinish.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.housefinish.R;

/**
 * Created by 徐余璟 on 2016/9/22.
 */
public class DetailsRightFragment extends BaseFragment{

    public static final String TAG = DetailsRightFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_details_right,container,false);
        return layout;
    }
}
