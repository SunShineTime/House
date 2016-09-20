package com.qianfeng.housefinish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.model.Home;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/20.
 */
public class HomeAdapter extends BaseAdapter {

    private List<Home>data;
    private LayoutInflater inflater;

    public HomeAdapter(Context context,List<Home> data) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (data!=null) {
            this.data = data;
        }else {
            this.data = new ArrayList<>();
        }
    }

    public void updateRes(List<Home>data){

    }

    public void addRes(List<Home>data){

    }


    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Home getItem(int position) {
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
            convertView = inflater.inflate(R.layout.home_item,parent,false);
            holder.image = (ImageView) convertView.findViewById(R.id.home_item_image);
            holder.title = (TextView) convertView.findViewById(R.id.home_item_title);
            holder.content = (TextView) convertView.findViewById(R.id.home_item_content);
            holder.count = (TextView) convertView.findViewById(R.id.home_item_count);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        x.image().bind(holder.image,getItem(position).getMainPic());




        return convertView;
    }

    public static class ViewHolder{
        ImageView image;
        TextView title;
        TextView content;
        TextView count;
    }
}
