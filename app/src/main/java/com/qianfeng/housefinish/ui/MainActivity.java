package com.qianfeng.housefinish.ui;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.fragments.AccountFragment;
import com.qianfeng.housefinish.fragments.BaseFragment;
import com.qianfeng.housefinish.fragments.CartFragment;
import com.qianfeng.housefinish.fragments.GoodsFragment;
import com.qianfeng.housefinish.fragments.HomeFragment;
import com.qianfeng.housefinish.fragments.MagicFragment;
import com.qianfeng.housefinish.utils.ToastUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.InvocationTargetException;


/**
 * 主页面
 *  字体：楷体
 */

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,Handler.Callback {

    private static final int EXIT = 100;
    private static final long DELAY_TIME = 3000;
    @ViewInject(R.id.main_radiogroup)
    private RadioGroup mRadioGroup;
    @ViewInject(R.id.main_fragment_home)
    private RadioButton mHome;
    @ViewInject(R.id.main_fragment_goods)
    private RadioButton mGoods;
    @ViewInject(R.id.main_fragment_magic)
    private RadioButton mMagic;
    @ViewInject(R.id.main_fragment_cart)
    private RadioButton mCart;
    @ViewInject(R.id.main_fragment_account)
    private RadioButton mAccount;
    private Fragment mFragment;
    private boolean isExit;
    private Handler mHandler=new Handler(this);
    private AssetManager mAssets;
    private Typeface mFromAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mAssets = getAssets();
        mFromAsset = Typeface.createFromAsset(mAssets, "fonts/kaiti.ttf");
        initView();
        ToastUtil.init(this);
    }

    private void initView() {
        //设置字体
        mHome.setTypeface(mFromAsset);
        mGoods.setTypeface(mFromAsset);
        mMagic.setTypeface(mFromAsset);
        mCart.setTypeface(mFromAsset);
        mAccount.setTypeface(mFromAsset);

        mRadioGroup.setOnCheckedChangeListener(this);
        //动态添加Fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mFragment = new HomeFragment();
        transaction.add(R.id.main_fragment, mFragment, HomeFragment.TAG);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_fragment_home:
                switchPages(HomeFragment.TAG, HomeFragment.class);
                break;
            case R.id.main_fragment_goods:
                switchPages(GoodsFragment.TAG, GoodsFragment.class);
                break;
            case R.id.main_fragment_magic:
                switchPages(MagicFragment.TAG, MagicFragment.class);
                break;
            case R.id.main_fragment_cart:
                switchPages(CartFragment.TAG, CartFragment.class);
                break;
            case R.id.main_fragment_account:
                switchPages(AccountFragment.TAG, AccountFragment.class);
                break;
        }

    }

    /**
     * 切换页面效果
     *
     * @param tag 添加碎片的标记
     * @param cls 添加碎片的Class
     */
    private void switchPages(String tag, Class<? extends BaseFragment> cls) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(mFragment);
        mFragment = fm.findFragmentByTag(tag);
        if (mFragment != null) {
            transaction.show(mFragment);
        } else {
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
            transaction.add(R.id.main_fragment, mFragment, tag);
        }
        transaction.commit();
    }

    //按返回键退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断按下的按钮是否是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                ToastUtil.toast("再按一次退出");
                isExit = true;
                mHandler.sendEmptyMessageDelayed(EXIT, DELAY_TIME);
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case EXIT:
                isExit = false;
                break;
        }
        return false;
    }
}
