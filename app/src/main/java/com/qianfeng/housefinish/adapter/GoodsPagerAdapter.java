package com.qianfeng.housefinish.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengchao on 2016/9/20.
 */
public class GoodsPagerAdapter extends PagerAdapter {
    private List<ImageView>data;

    public GoodsPagerAdapter(List<ImageView> data) {
        if (data!=null) {
            this.data = data;
        }else {
            this.data=new ArrayList<ImageView>();
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int tranPosition = position % data.size();
        container.removeView(data.get(tranPosition));


    }

    @Override
    public ImageView instantiateItem(ViewGroup container, int position) {
        int tranPosition=position%data.size();
        container.addView(data.get(tranPosition));
        return data.get(tranPosition);

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

}

