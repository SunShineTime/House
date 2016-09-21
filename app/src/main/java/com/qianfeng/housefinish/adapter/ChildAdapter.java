package com.qianfeng.housefinish.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.model.Proud;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 徐余璟 on 2016/9/21.
 */
public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    private List<Proud> data;
    private LayoutInflater inflater;

    public ChildAdapter(Context context, List<Proud> data) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }

    }

    public void updataRes(List<Proud> data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    public void addRes(List<Proud> data){
        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public Proud getItem(int position) {
        return data.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.goods_child_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        x.image().bind(holder.image,getItem(position).getProductPicUrl());
        holder.text.setText(getItem(position).getProductName());
        holder.price.setText(String.valueOf(getItem(position).getPrice()));
        holder.oldprice.setText(String.valueOf(getItem(position).getMarketingPrice()));
        double price = getItem(position).getPrice()*10 / getItem(position).getMarketingPrice();
        DecimalFormat df = new DecimalFormat("0.0");
        String string = df.format(price).toString();
        holder.zhekou.setText(string+"折");


    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.child_item_image)
        ImageView image;
        @ViewInject(R.id.child_item_text)
        TextView text;
        @ViewInject(R.id.child_item_price)
        TextView price;
        @ViewInject(R.id.child_item_oldprice)
        TextView oldprice;
        @ViewInject(R.id.child_item_zhekou)
        TextView zhekou;
        public ViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this,itemView);

        }
    }

}
