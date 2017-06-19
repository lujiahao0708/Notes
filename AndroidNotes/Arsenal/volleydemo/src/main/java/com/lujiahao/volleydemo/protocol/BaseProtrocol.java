package com.lujiahao.volleydemo.protocol;

import android.content.Context;

import com.android.volley.Request;
import com.lujiahao.l_library.engine.HttpLoader;
import com.lujiahao.l_library.engine.protocol.IProtocol;
import com.lujiahao.volleydemo.App;

/**
 * Created by lujiahao on 2016/5/16.
 */
public abstract class BaseProtrocol implements IProtocol {
    protected HttpLoader mHttpLoader;

    public BaseProtrocol() {
        this.mHttpLoader = App.HL;
    }

    public void doRequest(HttpLoader.HttpListener listener) {
        doRequest(mHttpLoader, listener);
    }
}
