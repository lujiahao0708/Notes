package com.qtparking.asynctaskdemo.imglru;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.qtparking.asynctaskdemo.R;
import com.qtparking.asynctaskdemo.encapsulate.AsyncLoader;
import com.qtparking.asynctaskdemo.encapsulate.OnLoadListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgLruActivity extends AppCompatActivity implements OnLoadListener<String, Void, Bitmap> {
    private ImageView ivImage;
    private ProgressBar progressBar;
    private ImgLruLoader mImgLruLoader;
    private String url = "http://att.bbs.duowan.com/forum/201410/11/1608565v9gvog9ybye5nnb.jpg";

    private AsyncLoader<String,Void,Bitmap> mLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_lru);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

//        mImgLruLoader = new ImgLruLoader(this);
//        mImgLruLoader.showImgAsycn(ivImage,url);

        // TODO 封装AsyncTask http://blog.csdn.net/gaoshouxiaodi/article/details/42556149

        int dataType = 0;
        mLoader = new AsyncLoader<String,Void,Bitmap>(dataType);
        mLoader.setOnLoadListener(this);
        mLoader.execute();
    }

    @Override
    public void onDataStart() {
        Log.i("mainactivity", "开始异步任务");
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public Bitmap doInWorkerThread(int dataType, String... params) throws Exception {
        return getBitmapFromURL(url);
    }

    @Override
    public void onDataProgress(Void... values) {
        Log.i("mainactivity", ""+values);
    }

    @Override
    public void onDataGet(Bitmap result) {
        Log.i("mainactivity", "获得异步任务结果");
        ivImage.setImageBitmap(result);
    }

    @Override
    public void onDataFail(Exception e) {
        Log.i("mainactivity", "异步任务失败");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDataFinish() {
        Log.i("mainactivity", "结束异步任务");
        progressBar.setVisibility(View.GONE);
    }

    public Bitmap getBitmapFromURL(String urlStr) {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(conn.getInputStream());
            SystemClock.sleep(1000);
            bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
