package com.lujiahao.l_library.engine;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数的封装
 * Todo 同时解决了Volley源码中post请求会多传一个&的bug(当然也可以在Volley源码中做出相应的修改)
 * Created by lujiahao
 * Created at 2016/5/16 13:49
 */
public class HttpParams{
    private final Map<String,String> mParams = new HashMap<>();
    private String CHARSET = "UTF-8";

    public HttpParams() {
        initParams();
    }

    /**
     * TODO 传入校验用的数据，例如appkey和imei号
     * 方便后面做校验
     */
    private void initParams() {
        //put("appKey","kdksljlfksdlkfskldfjskl");
    }

    /**
     * 重写put方法，扩展可以存int类型的数据
     *
     * @param key 参数的key
     * @param val 参数的value
     * @return 返回HttpParams本身，方便链式编程
     */
    public HttpParams put(String key, int val) {
        mParams.put(key, String.valueOf(val));
        return this;
    }

    /**
     * 重写put方法，扩展可以存double类型的数据
     */
    public HttpParams put(String key, double val) {
        mParams.put(key, String.valueOf(val));
        return this;
    }

    public HttpParams put(String key, String value){
        mParams.put(key, value);
        return this;
    }

    /**
     * 获取某个key对应的value
     * @param key key的名称
     * @return 返回某个key对应的value值
     */
    public String get(String key) {
        return mParams.get(key);
    }

    public String toGetParams() {
        StringBuilder buffer = new StringBuilder();
        if (!mParams.isEmpty()) {
            buffer.append("?");
            for (Map.Entry<String, String> entry : mParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                    continue;
                }
                try {
                    buffer.append(URLEncoder.encode(key, CHARSET));
                    buffer.append("=");
                    buffer.append(URLEncoder.encode(value, CHARSET));
                    buffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        String str = buffer.toString();
        // 去掉最后的&
        if (str.length() > 1 && str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 返回封装了httpparams的Map集合
     * @return
     */
    public Map<String,String> getParams(){
        return mParams;
    }
}
