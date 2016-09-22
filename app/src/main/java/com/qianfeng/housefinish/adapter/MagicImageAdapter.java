package com.qianfeng.housefinish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.model.MagicImage;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 软装图库适配器
 */
public class MagicImageAdapter extends BaseAdapter {

    private List<MagicImage>data;
    private LayoutInflater inflater;

    public MagicImageAdapter(Context context, List<MagicImage> data) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (data!=null) {
            this.data = data;
        }else {
            this.data = new ArrayList<>();
        }
    }

    public void updateRes(List<MagicImage>data){
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
    public MagicImage getItem(int position) {
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
            convertView = inflater.inflate(R.layout.magic_image_item,parent,false);
            holder.image = (ImageView) convertView.findViewById(R.id.magic_image_item_img);
            holder.title = (TextView) convertView.findViewById(R.id.magic_image_item_title);
            holder.content = (TextView) convertView.findViewById(R.id.magic_image_item_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        x.image().bind(holder.image,getItem(position).getMainPic());
        holder.title.setText(getItem(position).getStyleName());
        holder.content.setText(getItem(position).getEnName());

        return convertView;
    }

    public static class ViewHolder{
        ImageView image;
        TextView title;
        TextView content;
    }
}
