package com.qianfeng.housefinish.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.event.ActivityToFragment;
import com.qianfeng.housefinish.fragments.DetailsLeftFragment;
import com.qianfeng.housefinish.fragments.DetailsRightFragment;
import com.qianfeng.housefinish.model.LastDetails;
import com.qianfeng.housefinish.model.Name;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DetailsActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private static final String TAG = DetailsActivity.class.getSimpleName();
    private ImageView mMainImage;
    private TextView mTitle;
    private TextView mPrice;
    private TextView mOldPrice;
    private LinearLayout mName1;
    private LinearLayout mName2;
    private LinearLayout mName1More;
    private LinearLayout mName2More;
    private TextView mDianping;
    private LinearLayout mXiangqing;
    private String product;
    private String img;
    private double price;
    private double oldprice;
    private TextView mZhekou;
    private RadioGroup mRadioGroup;
    private View mLeftLine;
    private View mRightLine;
    private Fragment mShowFragment;
    private boolean ischeck;
    private boolean ischeck2;
    private int mID;
    private int mID2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        product = intent.getStringExtra("product");
        img = intent.getStringExtra("img");
        price = intent.getDoubleExtra("price",0);
        oldprice = intent.getDoubleExtra("oldprice",0);
        Log.e("传递过来的价格", "onCreate:原价----> "+oldprice+"现价---->"+price );

        initView();
        setView();
        initHttp();
    }



    private void initView() {
        mMainImage = (ImageView) findViewById(R.id.details_main_image);
        mTitle = (TextView) findViewById(R.id.details_main_title);
        mPrice = (TextView) findViewById(R.id.details_price);
        mOldPrice = (TextView) findViewById(R.id.details_oldprice);
        mZhekou = (TextView) findViewById(R.id.details_zhekou);

        mName1 = (LinearLayout) findViewById(R.id.details_name1);
        mName1More = (LinearLayout) findViewById(R.id.details_name1_more);
        mName2 = (LinearLayout) findViewById(R.id.details_name2);
        mName2More = (LinearLayout) findViewById(R.id.details_name2_more);
        mDianping = (TextView) findViewById(R.id.details_dianping);

        mRadioGroup = (RadioGroup) findViewById(R.id.details_radiogroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mLeftLine = findViewById(R.id.details_left_line);
        mRightLine = findViewById(R.id.details_right_line);

        //动态加载碎片
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mShowFragment = new DetailsLeftFragment();
        transaction.add(R.id.details_framlayout,mShowFragment,DetailsLeftFragment.TAG);
        transaction.commit();

    }

    private void setView() {
        x.image().bind(mMainImage,img);
        mPrice.setText(String.valueOf(price));
        mOldPrice.setText(String.valueOf(oldprice));
        double p = price * 10 / oldprice;
        DecimalFormat df = new DecimalFormat("0.0");
        String string = df.format(p).toString();
        mZhekou.setText(string+"折");
    }


    private void initHttp() {
        RequestParams params = new RequestParams("http://portal-web.zhaidou.com/product/getProductInfo.action?businessType=01&version=1.0.0&productId="+product);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                LastDetails lastDetails = gson.fromJson(result, LastDetails.class);
//                Log.e("请求数据log测试", "onSuccess: "+lastDetails.getData().getExpandedResponse().getProductInfoImages().get(0) );
                TextView name1 = new TextView(DetailsActivity.this);
                TextView name2 = new TextView(DetailsActivity.this);
                name1.setTextSize(18);
                name2.setTextSize(18);
                name1.setText(lastDetails.getData().getAttributeName1());
                name2.setText(lastDetails.getData().getAttributeName2());
                mName1.addView(name1);
                mName2.addView(name2);

                mDianping.setText(lastDetails.getData().getExpandedResponse().getProductDescription());


                //拿出选项标签
                List<Name> names = lastDetails.getData().getProductSKUArray();
                //拿出详情图片
                List<String> images = lastDetails.getData().getExpandedResponse().getProductInfoImages();

                ActivityToFragment event = new ActivityToFragment();
                event.setImages(images);
                EventBus.getDefault().post(event);

                initName( names);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initName(List<Name> names) {
        HashMap<String, String> color = new HashMap<>();
        HashMap<String, String> size = new HashMap<>();

        for (Name name : names) {
          color.put(name.getAttributeValue1(),name.getAttributeValue1());
            size.put(name.getAttributeValue2(),name.getAttributeValue2());
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,0,10,0);
        int id = 0;
        for (String string : color.values()) {
            TextView textView = new TextView(DetailsActivity.this);
            textView.setText(string);
            textView.setBackgroundResource(R.mipmap.item_01);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(layoutParams);
            textView.setId(id);
            id++;
            textView.setOnClickListener(this);
            mName1More.addView(textView);
        }
        for (String string : size.values()) {
            TextView textView = new TextView(DetailsActivity.this);
            textView.setText(string);
            textView.setBackgroundResource(R.mipmap.item_01);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(layoutParams);
            textView.setId(id);
            id++;
            textView.setOnClickListener(this);
            mName2More.addView(textView);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.details_left_rbtn:
                mLeftLine.setVisibility(View.VISIBLE);
                mRightLine.setVisibility(View.INVISIBLE);
                switchPages(DetailsLeftFragment.TAG,DetailsLeftFragment.class);
                break;
            case R.id.details_right_rbtn:
                mRightLine.setVisibility(View.VISIBLE);
                mLeftLine.setVisibility(View.INVISIBLE);
                switchPages(DetailsRightFragment.TAG,DetailsRightFragment.class);
                break;
        }
    }
    private void switchPages(String tag,Class<? extends Fragment> cls){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //隐藏显示页面
        transaction.hide(mShowFragment);
        //根据TAG去FragmentManager中进行查找
        mShowFragment= fm.findFragmentByTag(tag);
        //如果找到了，直接进行显示
        if (mShowFragment!=null) {
            transaction.show(mShowFragment);
        }else{
            //如果找不到，添加到父容器并设置一个标记
            try {
                //使用反射进行一个对象的实例化
                mShowFragment=cls.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.details_framlayout,mShowFragment,tag);
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (((LinearLayout) v.getParent()).getId()) {
            case R.id.details_name1_more:


                if (mID!=v.getId()) {
                    TextView view = (TextView) findViewById(mID);
                    view.setBackgroundResource(R.mipmap.item_01);
                    ischeck = false;
                    Log.e(TAG, "onClick: 不是同一个控件" );
                }
                if (!ischeck) {
                    v.setBackgroundResource(R.mipmap.item02);
                    ischeck = true;
                    Log.e(TAG, "onClick:当前控件 " );
                }else {
                    v.setBackgroundResource(R.mipmap.item_01);
                    ischeck = false;
                    Log.e(TAG, "onClick: " );
                }
                mID = v.getId();

                break;
            case R.id.details_name2_more:


                if (mID2!=v.getId()) {
                    TextView view2 = (TextView) findViewById(mID2);
                    view2.setBackgroundResource(R.mipmap.item_01);
                    ischeck2 = false;
                    Log.e(TAG, "onClick: 不是同一个控件" );
                }
                if (!ischeck2) {
                    v.setBackgroundResource(R.mipmap.item02);
                    ischeck2 = true;
                    Log.e(TAG, "onClick:当前控件 " );
                }else {
                    v.setBackgroundResource(R.mipmap.item_01);
                    ischeck2 = false;
                    Log.e(TAG, "onClick: " );
                }
                mID2 = v.getId();


                break;
        }
//        String s = ((TextView) v).getText().toString();
//        Toast.makeText(DetailsActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
