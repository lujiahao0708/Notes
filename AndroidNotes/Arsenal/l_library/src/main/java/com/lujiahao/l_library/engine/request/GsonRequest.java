package com.lujiahao.l_library.engine.request;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lujiahao.l_library.utils.FileUtil;
import com.lujiahao.l_library.utils.LLog;
import com.lujiahao.l_library.utils.MD5Utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 自定义Request，通过Gson解析json格式的response，带缓存请求功能
 * Created by lujiahao
 * Created at 2016/5/16 16:03
 */
public class GsonRequest<T> extends Request<T> {
    public final Gson gson = new Gson();
    private final Class<? extends T> mClazz;
    private final Map<String, String> mParams;
    private final Response.Listener<T> mListener;
    private boolean mIsCache;
    private Context mContext;

    public GsonRequest(int method, String url, Map<String, String> mParams, Class<? extends T> mClazz,
                       Response.ErrorListener listener,
                       Response.Listener<T> mListener, boolean mIsCache, Context mContext) {
        super(method, url, listener);
        this.mClazz = mClazz;
        this.mParams = mParams;
        this.mListener = mListener;
        this.mIsCache = mIsCache;
        this.mContext = mContext;
    }

    /**
     * 获取GsonRequest所要解析Class类型
     *
     * @return GSON解析的对象字节码
     */
    public Class<? extends T> getClazz() {
        return mClazz;
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            //String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            String json = new String(response.data, "UTF-8");// 如果后台指定好了返回的字符串的话就用上面的，没有就自己指定
            LLog.d(json);
            T result = gson.fromJson(json, mClazz);// 按正常响应解析
            if (mIsCache) {
                LLog.i("Save response to local!");// 如果解析成功，并且需要缓存则将json字符串缓存到本地
                FileUtil.copy(response.data, new File(mContext.getCacheDir(), "" + MD5Utils.encode(getUrl())));
            }
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (IOException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }
}
