package com.chiahaolu.settingitem;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Field;

/**
 * 布局填充工具
 * @author chiahao
 * @date 2015年12月11日10:22:14
 *
 */
public final class LayoutUtil {

    private static int sWidthPixels;
    private static int sHeightPixels;

    /**
     * Return real pixels represented by DIP
     *
     * @param context
     * @param dp
     * @return real pixels represented by DIP
     */
    public static int getPixelByDIP(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    public static int getDIPByPixel(Context context, int pixel) {
        return (int) (pixel / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int getPixelBySP(Context context, int sp) {
        return (int) (context.getResources().getDisplayMetrics().scaledDensity * sp);
    }

    public static void unLockScreenRotation(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    public static void setScreenRotation(Activity activity, boolean isLand) {
        if (isLand)
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void lockScreenRotaion(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        activity.setRequestedOrientation(display.getWidth() > display.getHeight() ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        int x;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
            return -1;
        }
    }

    public static int getScreenWidth(Context context) {
        getResolution(context);
        return Math.min(sWidthPixels, sHeightPixels);
    }

    private static void getResolution(Context context) {
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        sWidthPixels = display.widthPixels;
        sHeightPixels = display.heightPixels;
    }

    public static int getScreenHeight(Context context) {
        getResolution(context);
        return Math.max(sWidthPixels, sHeightPixels);
    }
}
