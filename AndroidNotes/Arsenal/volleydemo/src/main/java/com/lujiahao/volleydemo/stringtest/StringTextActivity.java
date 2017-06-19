package com.lujiahao.volleydemo.stringtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lujiahao.l_library.engine.HttpParams;
import com.lujiahao.l_library.utils.LLog;
import com.lujiahao.volleydemo.AppConstants;
import com.lujiahao.volleydemo.R;

public class StringTextActivity extends AppCompatActivity implements StringLoader.HttpCallBackInterface{

    private TextView textViewString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_text);
        textViewString = (TextView) findViewById(R.id.textViewString);

        String url = AppConstants.URL_COUPONS;
        HttpParams params = new HttpParams();
        params.put("phone","15613566958");
        StringLoader.getInstance(this).get(url, params, 0, this);
    }

    @Override
    public void onSuccess(int requestCode, String response) {
        switch (requestCode){
            case 0:
                LLog.e(response);
                break;
        }
    }

    @Override
    public void onFailed(int requestCode, String errMsg) {

    }
}
