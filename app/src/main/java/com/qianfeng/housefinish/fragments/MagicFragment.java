package com.qianfeng.housefinish.fragments;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.ui.ToastUtil;

/**
 * 软装魔法
 */
public class MagicFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = MagicFragment.class.getSimpleName();
    private Button mClassic;
    private AssetManager mAssets;
    private Typeface mFromAsset;
    private Button mChoose;
    private Button mImage;
    private Button mOnline;
    private Button mClassroom;
    private Button mDiy;
    private Button mKeFu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_magic,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAssets = getActivity().getAssets();
        mFromAsset = Typeface.createFromAsset(mAssets, "fonts/kaiti.ttf");
        initView();

    }

    private void initView() {
        mKeFu = (Button) layout.findViewById(R.id.magic_kefu);
        mKeFu.setTypeface(mFromAsset);
        mKeFu.setOnClickListener(this);
        mClassic = (Button) layout.findViewById(R.id.magic_classic_case);
        mClassic.setTypeface(mFromAsset);
        mClassic.setOnClickListener(this);
        mChoose = (Button) layout.findViewById(R.id.magic_choose_consult);
        mChoose.setTypeface(mFromAsset);
        mChoose.setOnClickListener(this);
        mImage = (Button) layout.findViewById(R.id.magic_image_case);
        mImage.setTypeface(mFromAsset);
        mImage.setOnClickListener(this);
        mOnline = (Button) layout.findViewById(R.id.magic_online_design);
        mOnline.setTypeface(mFromAsset);
        mOnline.setOnClickListener(this);
        mClassroom = (Button) layout.findViewById(R.id.magic_classroom);
        mClassroom.setTypeface(mFromAsset);
        mClassroom.setOnClickListener(this);
        mDiy = (Button) layout.findViewById(R.id.magic_diy);
        mDiy.setTypeface(mFromAsset);
        mDiy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.magic_kefu:
                ToastUtil.toast(""+mKeFu.getText());
                break;
            case R.id.magic_classic_case:
                ToastUtil.toast(""+mClassic.getText());
                break;
            case R.id.magic_choose_consult:
                ToastUtil.toast(""+mChoose.getText());
                break;
            case R.id.magic_image_case:
                ToastUtil.toast(""+mImage.getText());
                break;
            case R.id.magic_online_design:
                ToastUtil.toast(""+mOnline.getText());
                break;
            case R.id.magic_classroom:
                ToastUtil.toast(""+mClassroom.getText());
                break;
            case R.id.magic_diy:
                ToastUtil.toast(""+mDiy.getText());
                break;
        }
    }
}
