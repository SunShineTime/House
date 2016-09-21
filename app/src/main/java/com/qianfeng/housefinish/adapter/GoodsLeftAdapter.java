package com.qianfeng.housefinish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.model.Child;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/21.
 */
public class GoodsLeftAdapter extends BaseAdapter{

    private List<Child>data;
    private LayoutInflater inflater;

    public GoodsLeftAdapter(Context context, List<Child> data) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (data!=null) {
            this.data = data;
        }else {
            this.data = new ArrayList<>();
        }
    }

    public void updateRes(List<Child>data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Child getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.goods_left_item,parent,false);
            holder.text = (TextView) convertView.findViewById(R.id.goods_left_item_text);
            holder.image = (ImageView) convertView.findViewById(R.id.goods_left_item_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(position).getCategoryName());
        x.image().bind(holder.image,getItem(position).getCategoryPicUrl());

        return convertView;
    }

    public static class ViewHolder{
        ImageView image;
        TextView text;

    }
}
