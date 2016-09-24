package com.qianfeng.housefinish.adapter;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航页适配器
 */
public class GuideAdapter extends PagerAdapter{

    private List<ImageView> data;

    public GuideAdapter(List<ImageView> data) {
        if (data!=null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }
}
