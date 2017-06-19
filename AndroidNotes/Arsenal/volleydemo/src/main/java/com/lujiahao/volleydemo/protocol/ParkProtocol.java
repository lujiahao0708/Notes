package com.lujiahao.volleydemo.protocol;

import android.content.Context;

import com.lujiahao.l_library.engine.HttpLoader;
import com.lujiahao.l_library.engine.HttpParams;
import com.lujiahao.volleydemo.AppConstants;

/**
 * Created by lujiahao on 2016/5/17.
 */
public class ParkProtocol extends BaseProtrocol {
    private Context actContext;

    public ParkProtocol(Context actContext) {
        this.actContext = actContext;
    }

    @Override
    public void doRequest(HttpLoader loader, HttpLoader.HttpListener listener) {
        HttpParams params = new HttpParams()
        .put("pay_map_keys", 1)
        .put("customer_longitude", 30)
        .put("customer_latitude", 30)
        .put("pageno", 1);
        loader.get(actContext, AppConstants.URL_PARKS, params, AppConstants.REQUEST_CODE_PARKS, listener);
    }
}
