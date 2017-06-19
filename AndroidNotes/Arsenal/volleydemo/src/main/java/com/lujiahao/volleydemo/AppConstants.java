package com.lujiahao.volleydemo;

/**
 * 常量类 存储各种常量
 *  1.接口&请求码
 * Created by lujiahao
 * Created at 2016/5/16 14:15
 */
public class AppConstants {
    public static final String URL_SERVER = "http://www.dargonflyparking.com/qtparking";

    /**
     * 停车券列表请求地址&请求码
     */
    public static final String URL_COUPONS = URL_SERVER + "/app/coupons";
    public static final int REQUEST_CODE_COUPONS = 0;

    public static final String URL_PARKS = URL_SERVER + "/app/parks";
    public static final int REQUEST_CODE_PARKS = 1;

    public static final String URL_COM_PARK = URL_SERVER + "/app/parks/getusedpark";
    public static final int REQUEST_CODE_COM_PARK = 2;
}
