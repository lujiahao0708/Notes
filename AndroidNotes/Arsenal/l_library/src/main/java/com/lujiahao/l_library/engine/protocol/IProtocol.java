package com.lujiahao.l_library.engine.protocol;

import com.android.volley.Request;
import com.lujiahao.l_library.engine.HttpLoader;

/**
 * 封装网络请求的接口
 * Created by lujiahao
 * Created at 2016/5/16 13:28
 */
public interface IProtocol {
    /**
     * 封装参数，发起网络请求
     * @param loader    发起请求的HttpLoader对象
     * @param listener  处理请求的监听
     * @return  Requestr对象
     */
    void doRequest(HttpLoader loader,HttpLoader.HttpListener listener);
}
