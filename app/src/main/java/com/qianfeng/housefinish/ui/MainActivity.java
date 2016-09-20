package com.qianfeng.housefinish.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.fragments.AccountFragment;
import com.qianfeng.housefinish.fragments.CartFragment;
import com.qianfeng.housefinish.fragments.GoodsFragment;
import com.qianfeng.housefinish.fragments.HomeFragment;
import com.qianfeng.housefinish.fragments.MagicFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.InvocationTargetException;


/**
 * 主页面
 */

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @ViewInject(R.id.main_radiogroup)
    private RadioGroup mRadioGroup;
    private Fragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {

        mRadioGroup.setOnCheckedChangeListener(this);
        //动态添加Fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mFragment = new HomeFragment();
        transaction.add(R.id.main_fragment,mFragment,HomeFragment.TAG);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_fragment_home:
                switchPages(HomeFragment.TAG,HomeFragment.class);
                break;
            case R.id.main_fragment_goods:
                switchPages(GoodsFragment.TAG,GoodsFragment.class);
                break;
            case R.id.main_fragment_magic:
                switchPages(MagicFragment.TAG,MagicFragment.class);
                break;
            case R.id.main_fragment_cart:
                switchPages(CartFragment.TAG,CartFragment.class);
                break;
            case R.id.main_fragment_account:
                switchPages(AccountFragment.TAG,AccountFragment.class);
                break;
        }

    }

    /**
     * 切换页面效果
     * @param tag 添加碎片的标记
     * @param cls 添加碎片的Class
     */
    private void switchPages(String tag,Class<? extends BaseFragment> cls){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(mFragment);
        mFragment=fm.findFragmentByTag(tag);
        if (mFragment!=null) {
            transaction.show(mFragment);
        }else {
            try {
                mFragment = cls.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.main_fragment,mFragment,tag);
        }
        transaction.commit();
    }

}
