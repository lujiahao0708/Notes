package com.qtparking.asynctaskdemo;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new ProgressAsyncTask().execute();
    }

    class ProgressAsyncTask extends AsyncTask<Void,Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                // 将进度值传给onProgressUpdate方法
                publishProgress(i);
                SystemClock.sleep(300);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // 获取更新进度值
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
