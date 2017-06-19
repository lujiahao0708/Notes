package com.lujiahao.volleydemo.stringtest;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lujiahao.l_library.engine.HttpParams;

import java.util.Map;

/**
 * StringRequest访问的工具类
 * Created by lujiahao
 * Created at 2016/5/17 9:33
 */
public class StringLoader {
    private Context mContext;
    private RequestQueue mRequestQueue;
    private static StringLoader stringLoader;

    private StringLoader(Context context) {
        this.mContext = context;
        this.mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public static synchronized StringLoader getInstance(Context context) {
        if (stringLoader == null) {
            stringLoader = new StringLoader(context);
        }
        return stringLoader;
    }

    public Request<?> addRequest(Request<?> request) {
        if (mRequestQueue != null && request != null) {
            mRequestQueue.add(request);
        }
        return request;
    }

    public void cancelAllRequest(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public Request<?> get(String url,HttpParams params,AllHttpCallBack allHttpCallBack){
        return request(Request.Method.GET,url,params,allHttpCallBack);
    }

    public Request<?> get(String url,HttpParams params,int requestCode,HttpCallBackInterface httpCallBackInterface){
        return get(url,params,new AllHttpCallBack(requestCode,httpCallBackInterface));
    }

    public Request<?> post(String url,HttpParams params,AllHttpCallBack allHttpCallBack){
        return request(Request.Method.POST,url,params,allHttpCallBack);
    }

    private Request<?> request(int method,String url, final HttpParams params,AllHttpCallBack allHttpCallBack){
        StringRequest stringRequest = null;
        if (stringRequest == null) {
            if (method == Request.Method.GET){
                url = url + params.toGetParams();
            }
            stringRequest = new StringRequest(method,url,allHttpCallBack,allHttpCallBack){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params.getParams();
                }
            };
        }
        return addRequest(stringRequest);
    }

    private class AllHttpCallBack implements Response.Listener<String>,Response.ErrorListener{
        private HttpCallBackInterface httpCallBackInterface;
        private int requestCode;

        public AllHttpCallBack(int requestCode,HttpCallBackInterface httpCallBackInterface ) {
            this.httpCallBackInterface = httpCallBackInterface;
            this.requestCode = requestCode;
        }

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            httpCallBackInterface.onFailed(requestCode,volleyError.getMessage());
        }

        @Override
        public void onResponse(String response) {
            if (response == null) {
                httpCallBackInterface.onFailed(requestCode,"服务器返回数据为空");
            } else {
                httpCallBackInterface.onSuccess(requestCode, response);
            }

        }

    }

    interface HttpCallBackInterface{
        public void onSuccess(int requestCode,String response);
        public void onFailed(int requestCode,String errMsg);
    }

}
