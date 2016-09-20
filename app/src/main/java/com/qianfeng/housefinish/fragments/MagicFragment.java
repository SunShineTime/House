package com.qianfeng.housefinish.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.ui.BaseFragment;

/**
 * RecyclerView这是疏影0.0.00000000
 */
public class MagicFragment extends BaseFragment {

    public static final String TAG = MagicFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_magic,container,false);
        return layout;
    }
}
