package com.qianfeng.housefinish.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.event.ActivityToFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.x;

import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/22.
 */
public class DetailsLeftFragment extends BaseFragment{


   public static final String TAG = DetailsLeftFragment.class.getSimpleName();
    private LinearLayout mLineL;
    private List<String> images;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_details_left,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mLineL = ((LinearLayout) layout.findViewById(R.id.fragment_details_left));



    }



    @Subscribe
    public void onEvent(ActivityToFragment event){
        images = event.getImages();

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (images!=null&&images.size()!=0) {
            for (int i = 0; i < images.size(); i++) {
                ImageView imageView = new ImageView(getActivity());
                x.image().bind(imageView,images.get(i));
                imageView.setLayoutParams(params);
                mLineL.addView(imageView);
            }


        }else {
            Toast.makeText(getActivity(), "数据没有传过来", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);

    }
}
