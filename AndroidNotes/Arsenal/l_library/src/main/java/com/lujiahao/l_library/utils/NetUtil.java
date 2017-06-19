package com.lujiahao.l_library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

/**
 * 网络检测的工具类
 * Created by chiahao on 2016/1/13.
 */
public class NetUtil {

	/**
	 * 这样写有个好处是不用每次重复获得连接管理器了
	 * @param context 上下文信息
	 * @return 连接管理器的实体
	 */
	public static ConnectivityManager getConnectivityManager(Context context) {
        if (context != null) {
            return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return null;// 这样写比较好
	}

	private static WifiLock wifilock = null;

	/**
	 * 使用Wifi连接, wifi锁
	 */
	public static void startUseWifi(Context c) {
		WifiManager wifimanager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
		if (wifimanager != null) {
			wifilock = wifimanager.createWifiLock(WifiManager.WIFI_MODE_FULL, "meq");
			if (wifilock != null) {
				wifilock.acquire();
			}
		}
	}

	/**
	 * 释放wifi锁
	 */
	public static void stopUseWifi() {
		if (wifilock != null && wifilock.isHeld()) {
			wifilock.release();
		}
		wifilock = null;
	}

	/**
	 * 是否存在已经连接上的网络, 不论是wifi/cmwap/cmnet还是其他
	 * 
	 * @return
	 */
	public static boolean isConnected(Context c) {
		NetworkInfo ni = getConnectivityManager(c).getActiveNetworkInfo();
		return ni != null && ni.isConnected();
	}
//
//    public static boolean isWifiConnected(Context context){
//        NetworkInfo mWifiNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        if (mWifiNetworkInfo != null) {
//            return mWifiNetworkInfo.isAvailable();
//        }
//        return false;
//    }

}
