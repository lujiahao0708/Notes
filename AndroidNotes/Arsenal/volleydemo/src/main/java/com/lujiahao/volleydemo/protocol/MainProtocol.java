package com.lujiahao.volleydemo.protocol;

import android.content.Context;

import com.lujiahao.l_library.engine.HttpLoader;
import com.lujiahao.l_library.engine.HttpParams;
import com.lujiahao.volleydemo.AppConstants;

/**
 * Created by lujiahao on 2016/5/16.
 */
public class MainProtocol extends BaseProtrocol {

    private String phone;
    private Context actContext;

    public MainProtocol(Context actContext,String phone) {
        this.actContext = actContext;
        this.phone = phone;
    }

    @Override
    public void doRequest(HttpLoader loader, HttpLoader.HttpListener listener) {
        HttpParams params = new HttpParams()
                .put("phone", phone);
        loader.get(actContext, AppConstants.URL_COUPONS, params, AppConstants.REQUEST_CODE_COUPONS, listener);
    }
}
