package com.lujiahao.volleydemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lujiahao.l_library.engine.HttpLoader;


/**
 * 基类Activity 封装一些基本操作
 * Created by lujiahao
 * Created at 2016/5/18 10:52
 */
public abstract class BaseActivity extends AppCompatActivity implements HttpLoader.HttpListener {
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

    }

    @Override
    public void onGetResponseSuccess(int requestCode, String response) {
        onSuccess(requestCode, response);
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        switch (requestCode){
            case -1:
                Toast.makeText(BaseActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
        onError(requestCode, error);
    }

    protected abstract void onSuccess(int requestCode,String response);
    protected abstract void onError(int requestCode,VolleyError error);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }
}
