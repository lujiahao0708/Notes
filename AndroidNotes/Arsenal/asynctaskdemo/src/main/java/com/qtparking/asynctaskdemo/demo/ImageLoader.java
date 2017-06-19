package com.qtparking.asynctaskdemo.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.qtparking.asynctaskdemo.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lujiahao on 2016/4/8.
 */
public class ImageLoader {
    private ImageView mImageView;
    private String mUrl;

    private LruCache<String, Bitmap> mCaches = null;

    private ListView mListView;
    private Set<ImageAsyncTask> mTask;

    public ImageLoader(ListView listView) {
        mListView = listView;
        mTask = new HashSet<>();
        // 获取最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;// 可用内存为最大内存的四分之一
        // 匿名内部类的方式  LruCache
        mCaches = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }

    // 增加到缓存
    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            mCaches.put(url, bitmap);
        }
    }

    // 从缓存中获取bitmap
    public Bitmap getBitmapFromCache(String url) {
        return mCaches.get(url);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 当ImageView的url和传入的url相同的时候才设置bitmap
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    // 使用多线程的方式加载图片
    public void showImageByThread(ImageView imageView, final String url) {
        // 使用成员变量的方式来保存传入的imageview和url
        mImageView = imageView;
        mUrl = url;

        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromURL(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }.start();
    }

    public void showImageByAsycnTask(ImageView imageView, String url) {
        // 从缓存中取
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            // 缓存中没有，设置一个默认的图片
            imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            // 因这个在主线程中，所以可以使用
            imageView.setImageBitmap(bitmap);
        }
    }

    // 用来加载从start到end的图片
    public void loadImages(int start,int end){
        for (int i = start; i < end; i++) {
            String url = CourseAdapter.URLS[i];
            // 从缓存中取
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap == null) {
                // 缓存中没有，访问网络
                ImageAsyncTask task = new ImageAsyncTask(url);
                task.execute(url);
                mTask.add(task);// 存入一个管理里面 统一管理任务
            } else {
                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                // 因这个在主线程中，所以可以使用
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void cancelAllTasks() {
        if (mTask != null) {
            for (ImageAsyncTask task : mTask) {
                task.cancel(false);
            }
        }
    }

    private class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        //private ImageView mImageView;
        private String mUrl;

        public ImageAsyncTask(String url) {
            //mImageView = imageView;
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            // 从网络获取图片的bitmap
            Bitmap bitmap = getBitmapFromURL(url);
            // 将不再缓存的图片加入缓存
            if (bitmap != null) {
                addBitmapToCache(url,bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);// 从统一管理中取消这个任务
        }
    }

    /**
     * 通过url访问网络获取bitmap
     *
     * @param urlString
     * @return
     */
    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(conn.getInputStream());
            //SystemClock.sleep(1000);// 模拟弱网环境
            bitmap = BitmapFactory.decodeStream(is);
            conn.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
