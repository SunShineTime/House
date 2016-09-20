package com.qianfeng.housefinish.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 吐司工具类
 */
public class ToastUtil {

    private static Toast toast;

    public static void init(Context context){
        toast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
    }

    public static void toast(String conent){
        toast.setText(conent);
        toast.show();
    }

}
