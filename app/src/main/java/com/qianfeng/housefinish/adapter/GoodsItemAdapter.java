package com.qianfeng.housefinish.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.model.BigBigGoodsItem;
import com.qianfeng.housefinish.model.GoodsItemTwo;

import org.w3c.dom.Text;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chengchao on 2016/9/22.
 */
public class GoodsItemAdapter extends BaseAdapter {
    private static final String TAG = GoodsItemAdapter.class.getSimpleName();
    private List<GoodsItemTwo> data;
    private LayoutInflater inflater;

    public GoodsItemAdapter(Context context, List<GoodsItemTwo> data) {

        inflater=LayoutInflater.from(context);

        if (data!=null) {
            this.data = data;
        }else {
            this.data=new ArrayList<>();
        }
    }
    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void addRes(List<GoodsItemTwo> data) {
        if (data!=null) {
            this.data=data;
            notifyDataSetChanged();
        }
    }
    public void upData(List<GoodsItemTwo> data) {
        if (data!=null) {
            this.data.clear();
            this.data=data;
            notifyDataSetChanged();
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null) {
            convertView=inflater.inflate(R.layout.goods_item_listview,parent,false);
            holder=new ViewHolder();
            holder.imageView= ((ImageView) convertView.findViewById(R.id.goods_item_listview_image));
            holder.title= ((TextView) convertView.findViewById(R.id.goods_item_listview_title));
            holder.content= ((TextView) convertView.findViewById(R.id.goods_item_commemt));
            holder.price= ((TextView) convertView.findViewById(R.id.goods_item_price));
            holder.oldPrice= ((TextView) convertView.findViewById(R.id.goods_item_oldprice));
            holder.layout=convertView.findViewById(R.id.goods_layout);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if (position==0) {

            holder.layout.setVisibility(View.VISIBLE);
        }else {
            holder.layout.setVisibility(View.GONE);

        }
        //设置数据
        holder.title.setText(data.get(position).getProductName());
        holder.content.setText(data.get(position).getComment());
        holder.price.setText(data.get(position).getPrice());
        holder.oldPrice.setText(data.get(position).getMarketPrice());
        x.image().bind(holder.imageView,data.get(position).getProductPicUrl());
        return convertView;
    }
    public  class ViewHolder{
        ImageView imageView;
        TextView title;
        TextView content;
        TextView price;
        TextView oldPrice;
        View layout;

    }
}
