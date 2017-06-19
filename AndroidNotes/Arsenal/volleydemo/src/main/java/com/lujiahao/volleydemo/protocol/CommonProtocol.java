package com.lujiahao.volleydemo.protocol;

import android.content.Context;

import com.android.volley.Request;
import com.lujiahao.l_library.engine.HttpLoader;
import com.lujiahao.l_library.engine.HttpParams;
import com.lujiahao.volleydemo.AppConstants;

/**
 * Created by lujiahao on 2016/5/17.
 */
public class CommonProtocol extends BaseProtrocol {
    private String phone;
    private int btype;
    private Context actContext;
    public CommonProtocol(Context actContext,String phone, int btype) {
        this.actContext = actContext;
        this.phone = phone;
        this.btype = btype;
    }

    @Override
    public void doRequest(HttpLoader loader, HttpLoader.HttpListener listener) {
        HttpParams params = new HttpParams();
        params.put("phone", phone);
        params.put("btype", btype);
        loader.post(actContext,AppConstants.URL_COM_PARK,params,AppConstants.REQUEST_CODE_COM_PARK,listener);
    }
}
