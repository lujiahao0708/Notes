package com.lujiahao.volleydemo;

import android.app.Application;
import android.content.Context;

import com.lujiahao.l_library.engine.HttpLoader;

/**
 * Created by lujiahao on 2016/5/16.
 */
public class App extends Application {
    public static Context application;
    public static HttpLoader HL;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        // 初始化网络请求相关的核心类
        HL = HttpLoader.getInstance(this);
    }
}
