package com.qianfeng.housefinish.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/20.
 */
public  class PagerAdapter extends android.support.v4.view.PagerAdapter{

    private List<View> viewList;

    public PagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));

    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "title";
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));

        return viewList.get(position);
    }
}
