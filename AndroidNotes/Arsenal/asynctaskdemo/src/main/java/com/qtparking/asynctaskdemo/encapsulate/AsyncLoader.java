package com.qtparking.asynctaskdemo.encapsulate;

import android.os.AsyncTask;

/**
 * Created by lujiahao on 2016/4/8.
 */
public class AsyncLoader<P,S,T> extends AsyncTask<P,S,T> {
    private OnLoadListener<P,S,T> mListener;
    private int mDataType;
    private Exception ex;

    public AsyncLoader(int dataType) {
        mDataType = dataType;
        mListener = new OnLoadListener<P, S, T>() {
            @Override
            public void onDataStart() {

            }

            @Override
            public T doInWorkerThread(int dataType, P... params) throws Exception {
                return null;
            }

            @Override
            public void onDataProgress(S... values) {

            }

            @Override
            public void onDataGet(T result) {

            }

            @Override
            public void onDataFail(Exception e) {

            }

            @Override
            public void onDataFinish() {

            }
        };
    }

    public void setOnLoadListener(OnLoadListener<P,S,T> listener){
        if (listener != null) {
            mListener = listener;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mListener.onDataStart();
    }

    @Override
    protected T doInBackground(P... params) {
        try {
            return (T) mListener.doInWorkerThread(mDataType,params);
        } catch (Exception e) {
            e.printStackTrace();
            ex = e;
            return null;
        }
    }

    @Override
    protected void onProgressUpdate(S... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);
        if (ex != null) {
            mListener.onDataFail(ex);
        } else {
            mListener.onDataGet(t);
        }
        mListener.onDataFinish();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mListener.onDataFinish();
    }
}
