package com.chiahao.loginqq;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static final int FAIL = 2;
    private EditText etNameGet, etPwdGet, etNamePost, etPwdPost;
    private Button btnLoginGet, btnLoginPost;
    private String userName, pwd;

    private static final int SUCCESS = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    Toast.makeText(MainActivity.this, "服务器返回的数据-->" + (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                case FAIL:
                    Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNameGet = (EditText) findViewById(R.id.etNameGet);
        etPwdGet = (EditText) findViewById(R.id.etPwdGet);
        btnLoginGet = (Button) findViewById(R.id.btnLoginGet);
        etNamePost = (EditText) findViewById(R.id.etNamePost);
        etPwdPost = (EditText) findViewById(R.id.etPwdPost);
        btnLoginPost = (Button) findViewById(R.id.btnLoginPost);

        btnLoginGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                doGet();
                //doAsyncGet();
                doXutilsGet();
            }
        });
        btnLoginPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doPost();
                //doAsyncPost();
                doXutilsPost();
            }
        });
    }

    /**
     * GET方式访问网络
     */
    private void doGet() {
        userName = etNameGet.getText().toString().trim();
        pwd = etPwdGet.getText().toString().trim();
        final String server = "http://192.168.173.2:80/LearnPHP/login/GetServer.php";// 访问本机地址需要是静态地址
        new Thread() {
            @Override
            public void run() {
                try {
                    // 1.拼接并转换地址
                    String path = server + "?username=" + URLEncoder.encode(userName, "utf-8") + "&pwd=" + URLEncoder.encode(pwd, "utf-8");
                    URL url = new URL(path);
                    // 2.创建链接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // 3.设置请求方式和超时时间
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = conn.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        is.close();
                        baos.close();
                        conn.disconnect();
                        String result = new String(baos.toByteArray(), "utf-8");
                        // 组拼message对象
                        Message message = Message.obtain();
                        message.what = SUCCESS;
                        message.obj = result;
                        handler.sendMessage(message);// 发送message对象
                    }
                } catch (MalformedURLException e) {
                    Message message = new Message();
                    message.what = FAIL;
                    message.obj = "UTL异常";
                    handler.sendMessage(message);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * POST方式访问网络
     */
    private void doPost() {
        userName = etNamePost.getText().toString().trim();
        pwd = etPwdPost.getText().toString().trim();
        final String server = "http://192.168.173.2:80/LearnPHP/login/PostServer.php";// 访问本机地址需要是静态地址
        new Thread() {
            @Override
            public void run() {
                try {
                    //String path = server + "?username=" + URLEncoder.encode(userName, "utf-8") + "&pwd=" + URLEncoder.encode(pwd, "utf-8");
                    URL url = new URL(server);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // 1.设置请求方式为POST
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5000);
                    // 2.设置http请求数据类型为表单类型
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    // 3.设置给服务器的数据的长度-----即参数的长度
                    String params = "username=" + URLEncoder.encode(userName, "utf-8") + "&pwd=" + URLEncoder.encode(pwd, "utf-8");
                    conn.setRequestProperty("Content-Length", String.valueOf(params.length()));// 这里要注意一定要传长度，不然会造成413响应码错误滴
                    // 4.设置服务器可以写入数据
                    conn.setDoOutput(true);
                    // 5.开始向服务器写数据
                    //DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    OutputStream os = conn.getOutputStream();
                    os.write(params.getBytes());
                    os.flush();
                    os.close();

                    // 413 请求实体过大，超过了服务器响应的能力   params.getBytes()因为使用了这个方法返回的是一个byte数组，这样写道服务器的话会造成413错误
                    // 服务器响应
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = conn.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        is.close();
                        baos.close();
                        conn.disconnect();
                        String result = new String(baos.toByteArray(), "utf-8");
                        Message message = Message.obtain();
                        message.what = SUCCESS;
                        message.obj = result;
                        handler.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void doAsyncGet() {
        userName = etNameGet.getText().toString().trim();
        pwd = etPwdGet.getText().toString().trim();
        String path = "http://192.168.173.2:80/LearnPHP/login/GetServer.php" + "?username=" + userName + "&pwd=" + pwd;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(path, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(MainActivity.this, "服务器响应成功" + new String(responseBody), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "服务器响应失败" + new String(responseBody), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void doAsyncPost() {
        userName = etNamePost.getText().toString().trim();
        pwd = etPwdPost.getText().toString().trim();
        final String server = "http://192.168.173.2:80/LearnPHP/login/PostServer.php";// 访问本机地址需要是静态地址
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", userName);
        params.add("pwd", pwd);
        client.post(server, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(MainActivity.this, "服务器响应成功" + new String(responseBody), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "服务器响应失败" + new String(responseBody), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void doXutilsGet() {
        userName = etNameGet.getText().toString().trim();
        pwd = etPwdGet.getText().toString().trim();
        String path = "http://192.168.173.2:80/LearnPHP/login/GetServer.php" + "?username=" + userName + "&pwd=" + pwd;
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(MainActivity.this, "服务器响应成功" + responseInfo.result, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(MainActivity.this, "服务器响应失败" + s, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void doXutilsPost() {
        userName = etNamePost.getText().toString().trim();
        pwd = etPwdPost.getText().toString().trim();
        final String server = "http://192.168.173.2:80/LearnPHP/login/PostServer.php";
        com.lidroid.xutils.http.RequestParams params = new com.lidroid.xutils.http.RequestParams();
        params.addBodyParameter("userName", userName);
        params.addBodyParameter("pwd", pwd);

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.POST, server, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                parseJson(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(MainActivity.this, "服务器响应失败" + s, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void parseJson(String result) {
        Gson gson = new Gson();
        User user = new User();
        user = gson.fromJson(result, User.class);

        Toast.makeText(MainActivity.this, "服务器响应成功" + user.getData().getUsername(), Toast.LENGTH_SHORT).show();

    }

}
