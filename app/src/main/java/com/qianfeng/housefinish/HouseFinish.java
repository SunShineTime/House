package com.qianfeng.housefinish;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;
import org.xutils.x;

/**
 * Created by Administrator on 16-9-19.
 */
public class HouseFinish extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(true);


    }
}
