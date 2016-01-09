package com.lujiahao.mobilesafe.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * 安全显示Toast的工具类
 *      封装了runOnUiThread
 * Created by chiahao on 2015/7/28.
 */
public class ToastUtils {
    /**
     * 显示Toast，无论UI线程还是子线程均可更新
     * @param activity
     * @param content
     */
    public static void showSafeToast(final Activity activity, final String content){
        // 判断当前线程是否是UI线程
        if ("main".equals(Thread.currentThread().getName())){
            // 是UI线程，直接更新UI
            Toast.makeText(activity,content,Toast.LENGTH_SHORT).show();
        } else {
            // 子线程，使用runOnUiThread保证可以正常更新UI
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,content,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
