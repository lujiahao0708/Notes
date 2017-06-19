package com.qtparking.asynctaskdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadImageActivity extends AppCompatActivity {

    private ProgressBar mProgress;
    private ImageView mImageView;
    private static String URL = "https://ooo.0o0.ooo/2016/04/07/5706313b19ef8.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mImageView = (ImageView) findViewById(R.id.ivImage);

        new ImageAsycnTask().execute(URL);
    }

    class ImageAsycnTask extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // 1.通过可变参数获取url地址
            String url = params[0];
            Bitmap bitmap = null;
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                if (conn.getResponseCode() == 200){
                    InputStream is = conn.getInputStream();
                    SystemClock.sleep(2000);// 网络太快，慢一点看看progressbar
                    // 2.将流数据转化为bitmap
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 3.返回bitmap对象
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImageView.setImageBitmap(bitmap);
        }
    }
}
