package com.lujiahao.l_library.engine;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.lujiahao.l_library.engine.request.CustomStringRequest;
import com.lujiahao.l_library.utils.FileUtil;
import com.lujiahao.l_library.utils.LLog;
import com.lujiahao.l_library.utils.NetUtil;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 网络请求核心类，负责封装get/post请求(自定义GsonRequest)
 * 支持添加自定义Request，初始化RequestQueue以及ImageLoader
 * Created by lujiahao
 * Created at 2016/5/16 13:38
 */
public class HttpLoader {
    /**
     * 保存ImageView上正在发起的网络请求
     */
    private final Map<ImageView, ImageLoader.ImageContainer> mImageContainers = new HashMap<>();
    private static HttpLoader sInstance;
    /**
     * 过滤重复请求，保存当前正在消息队列中执行的Request.key对应的requestCode
     */
    private final HashMap<Integer, Request<?>> mInFlightRequests = new HashMap<>();
    /**
     * 消息队列，全局使用一个
     */
    private RequestQueue mRequestQueue;
    /**
     * 图片加载工具，自定义缓存机制
     */
    private ImageLoader mImageLoader;
    private Context appContext;

    public HttpLoader(Context context) {
        this.appContext = context.getApplicationContext();
        this.mRequestQueue = Volley.newRequestQueue(appContext);
        //this.mImageLoader = new ImageLoader(mRequestQueue,new VolleyImageCacheImpl(appContext));
    }

    /**
     * 返回HttpLoader单例对象
     *
     * @param context 上下文
     * @return HttpLoader单例对象
     */
    public static synchronized HttpLoader getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HttpLoader(context);
        }
        return sInstance;
    }

    /**
     * 添加一个请求到请求队列，支持任意类型的Request
     *
     * @param request 任意类型的request请求
     * @return 返回该Request，方便链式调用
     */
    public Request<?> addRequest(Request<?> request) {
        if (mRequestQueue != null && request != null) {
            mRequestQueue.add(request);
        }
        return request;
    }

    /**
     * 添加一个HttpLoader管理和创建的Request请求到请求duilie
     *
     * @param request     任意类型的Request请求
     * @param requestCode 请求的唯一标识码
     * @return 返回该Request，方便链式调用
     */
    private Request<?> addRequest(Request<?> request, int requestCode) {
        if (mRequestQueue != null && request != null) {
            mRequestQueue.add(request);
            mInFlightRequests.put(requestCode, request);// 将当前的Request添加到正在处理的请求中
        }
        return request;
    }

    /**
     * 取消请求
     *
     * @param tag 请求的TAG
     */
    public void cancelRequest(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);// 从请求队列中取消对应的任务
        }
        // 同时在mInFlightRequests删除保存所有TAG匹配的Request
        Iterator<Map.Entry<Integer, Request<?>>> iterator = mInFlightRequests.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Request<?>> entry = iterator.next();
            Object rTag = entry.getValue().getTag();
            if (rTag != null && rTag.equals(tag)) {
                iterator.remove();
            }
        }
    }

    /**
     * 返回已经初始化过的ImageLoader类
     *
     * @return ImageLoader对象
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * 请求网络图片并设置给ImageView
     *
     * @param view       需要加载网络图片的ImageView
     * @param requestUrl 图片的网络地址
     */
    public void display(ImageView view, String requestUrl) {
        display(view, requestUrl, 0, 0);
    }

    /**
     * 请求网络图片并设置给ImageView，可以设置默认显示图片和加载错误的图片
     *
     * @param view              需要加载网络图片的ImageView
     * @param requestUrl        图片的网络地址
     * @param defaultImageResId 默认显示的图片
     * @param errorImageResId   加载错误显示的图片
     */
    public void display(ImageView view, String requestUrl, int defaultImageResId, int errorImageResId) {
        display(view, requestUrl, defaultImageResId, errorImageResId, view.getWidth(), view.getHeight(), ImageView.ScaleType.FIT_XY);
    }

    /**
     * 发起图片的网络请求
     *
     * @param view              需要加载网络图片的ImageView
     * @param requestUrl        图片的网络地址
     * @param defaultImageResId 默认显示的图片
     * @param errorImageResId   加载错误显示的图片
     * @param maxWidth          图片的最大宽度
     * @param maxHeight         图片的最大高度
     * @param scaleType         图片的缩放类型
     */
    @SuppressLint("NewApi")
    public void display(final ImageView view, final String requestUrl, final int defaultImageResId, final int errorImageResId, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
        if (mImageContainers.containsKey(view)) {// 如果已经给该View请求了一张网络图片
            mImageContainers.get(view).cancelRequest();// 那么就把之前的取消掉，保证一个ImageView身上只有一个任务
        }
        ImageLoader.ImageContainer imageContainer = mImageLoader.get(requestUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    view.setImageBitmap(response.getBitmap());
                    ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).setDuration(800).start();// 渐变动画
                    mImageContainers.remove(view);// 请求成功，移除
                } else if (defaultImageResId != 0) {
                    view.setImageResource(defaultImageResId);
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (errorImageResId != 0) {
                    view.setImageResource(errorImageResId);
                    ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).setDuration(800).start();// 渐变动画
                }
                mImageContainers.remove(view);// 请求失败，移除view身上的任务
            }
        }, maxWidth, maxHeight);//, scaleType);  导入了一个旧的volley包就不需要这个了
        mImageContainers.put(view, imageContainer);// 将view身上的请求任务进行保存
    }


    public Request<?> get(Context actContext, String url, HttpParams params, int requestCode, HttpListener listener) {
        return request(actContext, Request.Method.GET, url, params, requestCode, listener, true);
    }

    /**
     * GET方式的GsonRequest请求，默认缓存请求结果
     *
     * @param url         请求地址
     * @param params      GET请求参数，拼接在url后面，可以为null
     * @param requestCode 请求码 每次请求对应一个code作为该Request的唯一标示
     * @param listener    处理响应的监听器
     * @param isCache     是否需要缓存本次响应的结果，没有网络时会使用本地缓存
     * @return Request请求对象
     */
    public Request<?> get(Context actContext, String url, HttpParams params, int requestCode, HttpListener listener, boolean isCache) {
        return request(actContext, Request.Method.GET, url, params, requestCode, listener, isCache);
    }

    /**
     * POST方式的GsonRequest请求，默认不使用缓存
     *
     * @param url         请求地址
     * @param params      GET请求参数，拼接在url后面，可以为null
     * @param requestCode 请求码 每次请求对应一个code作为该Request的唯一标示
     * @param listener    处理响应的监听器
     * @return Request请求对象
     */
    public Request<?> post(Context actContext, String url, HttpParams params, int requestCode, HttpListener listener) {
        return request(actContext, Request.Method.POST, url, params, requestCode, listener, false);// POST请求默认不使用缓存
    }

    /**
     * 发送GsonRequest请求
     *
     * @param method      请求方式
     * @param url         请求地址
     * @param params      请求参数，可以为null
     * @param requestCode 请求码 每次请求对应一个code作为该Request的唯一标示
     * @param listener    处理响应的监听
     * @param isCache     是否需要缓存本次响应的结果
     * @return Request请求对象
     */
    private Request<?> request(Context actContext, int method, String url, HttpParams params, int requestCode, HttpListener listener, boolean isCache) {
        if (!NetUtil.isConnected(appContext)) {
            cancelRequest(listener);
            listener.onGetResponseError(-1, new VolleyError("网络未连接"));
            return null;
        } else {
            LLog.d("Request URL:" + url);
            Request request = mInFlightRequests.get(requestCode);// 从正在执行的请求集合中取得请求
            if (request == null) {
                // 创建新的网络请求  一般服务器返回的都是Gson数据，但是我们公司的坑比后台返的数据乱七八糟  只能使用StringRequest
                // 没办法解析数据就会下放到界面成  坑比后台
                request = makeCustomStringRequest(actContext, method, url, params, requestCode, listener, isCache);//.setTag(listener);
                if (method == Request.Method.GET) {
                    // 如果是GET请求，则首次尝试解析本地缓存提供的界面显示，然后在发起网络请求
                    tryLoadCacheResponse(request, requestCode, listener);
                }
                LLog.i("Handle request by network!");
                return addRequest(request, requestCode);
            } else {
                LLog.i("Hi guy,the request (RequestCode is " + requestCode + ")  is already in-flight , So Ignore!");
                return request;
            }
        }
    }



    /**
     * 初始化一个CustomStringRequest
     *
     * @param method      请求方式
     * @param url         url地址
     * @param params      请求参数，可null
     * @param requestCode 请求码 每次请求对应一个code作为改Request的唯一标示
     * @param listener    监听器用来响应结果
     * @param isCache     是否缓存
     * @return Request请求对象
     */
    private CustomStringRequest makeCustomStringRequest(Context actContext, int method, String url, final HttpParams params, int requestCode, HttpListener listener, boolean isCache) {
        ResponseListener responseListener = new ResponseListener(requestCode, listener);
        Map<String, String> paramsMap = null;// 默认为null
        if (params != null) {// 如果有参数，则构建参数
            if (method == Request.Method.GET) {
                url = url + params.toGetParams();// GET请求，直接拼接url
            } else {
                paramsMap = params.getParams();// POST请求，取出Httpparams中的Map参数集合
            }
        }
        CustomStringRequest request = new CustomStringRequest(actContext, method, url, paramsMap, responseListener, responseListener, isCache, appContext);
        request.setRetryPolicy(new DefaultRetryPolicy());// 设置超时时间，重试次数，重试因子（1,1*2,2*2,4*2）等
        request.setTag(listener);
        return request;
    }

    /**
     * 尝试从缓存中读取json数据
     *
     * @param request 要寻找缓存的request
     */
    private void tryLoadCacheResponse(Request request, int requestCode, HttpListener listener) {
        LLog.i("Try to  load cache response first !");
        if (listener != null && request != null) {
            try {
                File cacheFile = FileUtil.getFile(appContext, request.getUrl());// 获取缓存文件
                StringWriter sw = new StringWriter();
                // 读取缓存文件
                FileUtil.copy(new FileReader(cacheFile), sw);
                if (request instanceof CustomStringRequest) {
                    // 如果是GsonRequest，那么解析出本地缓存的json数据为GsonRequest
                    CustomStringRequest ctsr = (CustomStringRequest) request;
                    String response = sw.toString();
                    // 传递onResponse，让前面的人用缓存数据
                    listener.onGetResponseSuccess(requestCode, response);
                    LLog.i("Load cache response success !");
                }
            } catch (Exception e) {
                LLog.w("No cache response ! " + e.getMessage());
            }
        }
    }

    /**
     * 成功获取到服务器响应的结果的监听，供UI层注册
     */
    public interface HttpListener {
        /**
         * 当成功获取到服务器响应结果的时候调用
         *
         * @param requestCode response对应的requestCode
         * @param response    返回的response
         */
        void onGetResponseSuccess(int requestCode, String response);

        /**
         * 网络请求失败，做一些释放性的操作，比如关闭对话框等
         *
         * @param requestCode response对应的requestCode
         * @param error       异常详情
         */
        void onGetResponseError(int requestCode, VolleyError error);
    }

    /**
     * ResponseListener，封装了Volley的错误和成功的回调监听，并执行一些默认处理，同时会将事件通过HttpListener分发到UI层
     */
    private class ResponseListener implements Response.ErrorListener, Response.Listener<String> {
        private HttpListener listener;
        private int requestCode;

        public ResponseListener(int requestCode, HttpListener listener) {
            this.listener = listener;
            this.requestCode = requestCode;
        }

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            LLog.w("Request error from network!");
            volleyError.printStackTrace();
            mInFlightRequests.remove(requestCode);// 请求错误，从请求集合中删除该请求
            if (listener != null) {
                listener.onGetResponseError(requestCode, volleyError);
            }
        }

        @Override
        public void onResponse(String response) {
            mInFlightRequests.remove(requestCode);// 请求成功，从请求集合中删除该请求
            if (response != null) {
                //SystemClock.sleep(2000);
                LLog.i("Request success from network!");
                try {
                    JSONObject jsonObject = new JSONObject(response);// 处理分发数据---解析json
                    String status = jsonObject.getString("status");
                    if (listener != null && response != null) {// 分发数据
                        if (("success".equals(status) || "ok".equals(status))) {
                            String data = null;
                            try {
                                data = jsonObject.getString("data");
                                listener.onGetResponseSuccess(requestCode, data);
                            } catch (Exception e) {
                                // 不是标准格式的时候就把原始数据传回去
                                listener.onGetResponseSuccess(requestCode, response);
                            }
                        } else if (("fail".equals(status) || "error".equals(status))) {
                            String errMsg = null;
                            try {
                                errMsg = jsonObject.getString("msg");
                            } catch (Exception e) {
                                errMsg = jsonObject.getString("message");
                            } finally {
                                listener.onGetResponseError(requestCode, new VolleyError(errMsg));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onGetResponseError(requestCode, new VolleyError("解析问题"));
                }
            }
        }
    }


}
