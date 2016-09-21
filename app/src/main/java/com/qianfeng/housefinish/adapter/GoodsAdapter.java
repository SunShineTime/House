package com.qianfeng.housefinish.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.model.Goods;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengchao on 2016/9/20.
 */
public class GoodsAdapter extends BaseAdapter {
    private static final String TAG = GoodsAdapter.class.getSimpleName();
    private List<Goods> data;
    private LayoutInflater inflater;

    public GoodsAdapter(Context context,List<Goods> data) {
        inflater=LayoutInflater.from(context);
        if (data!=null) {
            this.data = data;
        }else{
            this.data=new ArrayList<>();
        }
    }
    //上拉加载
    public void addRes(List<Goods> data) {
        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();

        }
    }
//下拉刷新
    public void upData(List<Goods> data) {
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
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null) {
            convertView=inflater.inflate(R.layout.fragment_goods_listview,parent,false);
            holder=new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.fragment_goods_listview_image);
            holder.textpay = (TextView) convertView.findViewById(R.id.fragment_goods_listview_text_pay);
           holder.textTitle = (TextView) convertView.findViewById(R.id.fragment_goods_listview_text_title);
           holder.textTime = (TextView) convertView.findViewById(R.id.fragment_goods_listview_text_time);
            holder.textView= ((TextView) convertView.findViewById(R.id.fragment_goods_listview_title));
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if (position==0) {
            holder.textView.setVisibility(View.VISIBLE);
        }else {
            holder.textView.setVisibility(View.GONE);
        }
        //设置数据
        holder.textpay.setText(data.get(position).getDiscountLabel());
        holder.textTitle.setText(data.get(position).getActivityName());
//        Log.e(TAG, "getView: "+data.get(position).getActivityName() );
        x.image().bind(holder.imageView,data.get(position).getMainPic());
        return convertView;
    }


    public  class ViewHolder{

        ImageView imageView;

        TextView textpay;

        TextView textTitle;

        TextView textTime;

        TextView textView;
    }
}
