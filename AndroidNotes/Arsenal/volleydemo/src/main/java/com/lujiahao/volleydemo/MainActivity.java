package com.lujiahao.volleydemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lujiahao.l_library.utils.LLog;
import com.lujiahao.volleydemo.protocol.CommonProtocol;
import com.lujiahao.volleydemo.protocol.MainProtocol;
import com.lujiahao.volleydemo.protocol.ParkProtocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends BaseActivity{

    private Button btnDoRequest;
    private TextView textViewResult;
    private MainProtocol protocol;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDoRequest = (Button) findViewById(R.id.btnDoRequest);
        textViewResult = (TextView) findViewById(R.id.textviewResult);
        btnDoRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainProtocol(MainActivity.this,"15613566958").doRequest(MainActivity.this);
                new ParkProtocol(MainActivity.this).doRequest(MainActivity.this);
                new CommonProtocol(MainActivity.this,"15613566958",1).doRequest(MainActivity.this);
            }
        });
    }

    @Override
    protected void onSuccess(int requestCode, String response) {
        switch (requestCode){
            case AppConstants.REQUEST_CODE_COUPONS:
                LLog.e(response);
                break;
            case AppConstants.REQUEST_CODE_PARKS:
                LLog.e(response);
                break;
            case AppConstants.REQUEST_CODE_COM_PARK:
                LLog.e(response);
                break;
        }
    }

    @Override
    protected void onError(int requestCode, VolleyError error) {
        switch (requestCode){
            case AppConstants.REQUEST_CODE_COUPONS:
                Toast.makeText(MainActivity.this,error.getMessage()+"COUPONS",Toast.LENGTH_SHORT).show();
                break;
            case AppConstants.REQUEST_CODE_PARKS:
                Toast.makeText(MainActivity.this,error.getMessage()+"PARKS",Toast.LENGTH_SHORT).show();
                break;
            case AppConstants.REQUEST_CODE_COM_PARK:
                Toast.makeText(MainActivity.this,error.getMessage()+"COM_PARK",Toast.LENGTH_SHORT).show();
                break;
        }
    }















    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}
