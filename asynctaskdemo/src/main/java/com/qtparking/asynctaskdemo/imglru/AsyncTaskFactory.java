package com.qtparking.asynctaskdemo.imglru;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by chiahao on 2016/4/10.
 */
public class AsyncTaskFactory {

    private final static String TAG = "AsyncTaskFactory # init";

    /**
     * Asynchronous task to return the results after treatment
     * @author wei.zhou
     */
    public static class AsyncResult {
        // url
        public String _url;
        // byte[]
        public byte[] _byteBuffer;
    }

    /**
     * Callback processing is complete
     * @author wei.zhou
     */
    public static interface IProgressCallback {

        /**
         * Time-consuming operation
         */
        public void progressCallback(AsyncResult result);
    }

    /**
     * Asynchronous callback interface after the task
     *
     * @author wei.zhou
     */
    public static interface IResultCallback {

        /**
         * Synchronous Callback
         *
         * @param result
         */
        public void resultCallback(AsyncResult result);

    }

// //////////////////////////////////////////////////////////
// ***************
// //////////////////////////////////////////////////////////

    /**
     * Asynchronous downloads package data downloaded from the network and returns the result as a byte [] array
     *
     * @author wei.zhou
     */
    private class AsyncDownLoaderTask extends android.os.AsyncTask<String, Integer, AsyncResult> {

        /**
         * synchronous Callback
         */
        private IResultCallback _resultCallback;

        /**
         * ProgressCallback
         */
        private IProgressCallback _progressCallback;

        /**
         * The parameters of the implementation of,url...., the method running in the asynctask thread
         */
        @Override
        protected AsyncResult doInBackground(String... params) {
            final AsyncResult result = new AsyncResult();
            result._url = params[0];
            result._byteBuffer = download(params[0]);
            // callback processing
            if (_progressCallback != null)
                _progressCallback.progressCallback(result);
            return result;
        }

        /**
         * call by main thread
         * Do not do anything in the time-consuming method of operation within
         * The results
         */
        @Override
        protected void onPostExecute(AsyncResult result) {
            // After the implementation of the callback
            if (_resultCallback != null)
                _resultCallback.resultCallback(result);
        }

        /**
         *
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        /**
         * The implementation of the callback interface to receive
         *
         * @param callback
         * @param params
         */
        public final void execute(IProgressCallback proCallback, IResultCallback callback, String... params) {
            _progressCallback = proCallback;
            _resultCallback = callback;
            execute(params);
        }

        /**
         * Download Data
         *
         * @param _url
         * @return
         */
        public final byte[] download(String _url) {
            URL url = null;
            HttpURLConnection conn = null;
            InputStream input = null;
            ByteArrayOutputStream out = null;
            byte[] r_buffer = null;
            try {
                url = new URL(_url);
                conn = (HttpURLConnection) url.openConnection();
                input = conn.getInputStream();
                out = new ByteArrayOutputStream();
                byte buf[] = new byte[1024 * 256];
                int len;
                while ((len = input.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                r_buffer = out.toByteArray();
                out.close();
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                url = null;
                conn = null;
                input = null;
                out = null;
            }
            return r_buffer;
        }
    }

    /** */
    private static AsyncTaskFactory _instance;

    /**
     * Private constructor
     */
    private AsyncTaskFactory() {
        System.out.println(TAG);
    }

    /**
     * Get instance object
     *
     * @return
     */
    public static final AsyncTaskFactory getInstance() {
        if (_instance == null)
            _instance = new AsyncTaskFactory();
        return _instance;
    }

    /**
     * Add asynchronous task
     *
     * @param proCallback : Time-consuming operation, read, write IO......,call by async task thread
     * @param resCallback : Not time-consuming operation, call by main UI thread
     * @param params:url1
     */
    public final void addSyncTask(IProgressCallback proCallback, IResultCallback resCallback, String... params) {
        new AsyncDownLoaderTask().execute(proCallback, resCallback, params);
    }
}
