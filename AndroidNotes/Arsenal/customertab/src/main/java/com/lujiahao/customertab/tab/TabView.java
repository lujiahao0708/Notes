package com.lujiahao.customertab.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lujiahao.customertab.R;

/**
 * 每个Tab的单独的内容
 * Created by lujiahao
 * Created at 2016/5/23 16:39
 */
public class TabView extends LinearLayout implements View.OnClickListener {
    private ImageView mTabImage;
    private TextView mTabLable;

    public TabView(Context context) {
        super(context);
        initView(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.item_tab,this,true);
        mTabImage = (ImageView) findViewById(R.id.ivTabImage);
        mTabLable = (TextView) findViewById(R.id.tvTabLable);
    }

    public void initData(TabItem tabItem){
        mTabImage.setImageResource(tabItem.imageResId);
        mTabLable.setText(tabItem.lableResId);
    }

    @Override
    public void onClick(View v) {

    }

    public void onDataChanged(int badgeCont){
        //  TODO notify new message, change the badgeView
    }
}
