package com.qianfeng.housefinish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 徐余璟 on 2016/9/21.
 */
public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {


    @Override
    public int getItemCount() {
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }



    public static class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
