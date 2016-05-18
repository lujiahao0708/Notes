package com.lujiahao.l_library.engine.request;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.lujiahao.l_library.utils.FileUtil;
import com.lujiahao.l_library.utils.LLog;
import com.lujiahao.l_library.view.ProgressDialogFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by lujiahao on 2016/5/17.
 */
public class CustomStringRequest extends StringRequest {
    private final Map<String, String> mParams;
    private final Response.Listener<String> mListener;
    private boolean mIsCache;
    Context actContext;
    private Context appContext;

    public CustomStringRequest(Context actContext,int method, String url, Map<String, String> mParams,
                       Response.ErrorListener errorListener,
                       Response.Listener<String> mListener, boolean mIsCache, Context appContext) {
        super(method,url,mListener,errorListener);
        this.mParams = mParams;
        this.mListener = mListener;
        this.mIsCache = mIsCache;
        ProgressDialogFactory.getInstance(actContext).showProgressDialog();
        this.appContext = appContext;
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            //String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            String result = new String(response.data, "UTF-8");// 如果后台指定好了返回的字符串的话就用上面的，没有就自己指定
            LLog.d(result);
            if (mIsCache) {
                LLog.i("Save response to local!");// 如果解析成功，并且需要缓存则将json字符串缓存到本地
                FileUtil.copy(response.data, FileUtil.getFile(appContext,getUrl()));
            }
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (IOException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        if (mListener != null) {
            //progressDialog.dismiss();
            ProgressDialogFactory.getInstance(actContext).disMissDialog();
            mListener.onResponse(response);
        }
    }
}
