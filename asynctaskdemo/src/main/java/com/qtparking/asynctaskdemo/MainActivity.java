package com.qtparking.asynctaskdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLoadImage,btnProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoadImage = (Button) findViewById(R.id.btnLoadImage);
        btnProgress = (Button) findViewById(R.id.btnProgress);


        btnLoadImage.setOnClickListener(this);
        btnProgress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoadImage:
                startActivity(new Intent(this,LoadImageActivity.class));
                break;
            case R.id.btnProgress:
                startActivity(new Intent(this,ProgressActivity.class));
                break;
        }
    }
}
