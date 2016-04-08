package com.qtparking.asynctaskdemo.encapsulate;

/**
 * Created by lujiahao on 2016/4/8.
 */
public interface OnLoadListener<P, S, T> {
    void onDataStart();

    T doInWorkerThread(int dataType, P... params) throws Exception;

    void onDataProgress(S... values);

    void onDataGet(T result);

    void onDataFail(Exception e);

    void onDataFinish();
}
