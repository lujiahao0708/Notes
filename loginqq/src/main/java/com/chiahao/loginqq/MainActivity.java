package com.chiahao.loginqq;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private EditText etNameGet,etPwdGet,etNamePost,etPwdPost;
    private Button btnLoginGet,btnLoginPost;
    private String userName,pwd;

    private static final int SUCCESS = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SUCCESS:
                    Toast.makeText(MainActivity.this,"服务器访问成功",Toast.LENGTH_SHORT).show();
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
                doGet();
            }
        });
        btnLoginPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPost();
            }
        });
    }

    /**
     * GET方式访问网络
     */
    private void doGet() {
        userName = etNameGet.getText().toString().trim();
        pwd = etPwdGet.getText().toString().trim();
        String server = "http://192.168.91.2:80/LearnPHP/login/server.php";// 访问本机地址需要是静态地址
        final String path = server + "?username="+userName+"&pwd="+pwd;
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200){
                        InputStream is = conn.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read()) != -1){
                            baos.write(buffer,0,len);
                        }
                        is.close();
                        baos.close();
                        conn.disconnect();
                        String result = new String(baos.toByteArray(),"UTF-8");
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

    /**
     * POST方式访问网络
     */
    private void doPost() {

    }
}
