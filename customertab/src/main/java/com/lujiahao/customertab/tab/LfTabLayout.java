package com.lujiahao.customertab.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Tab的父容器,容纳所有的TabView
 * Created by lujiahao
 * Created at 2016/5/23 16:39
 */
public class LfTabLayout extends LinearLayout implements View.OnClickListener {
    private ArrayList<TabItem> tabs;
    private OnTabClickListener listener;
    private View selectedView;
    private int tabCount;

    public LfTabLayout(Context context) {
        super(context);
        initView();
    }

    public LfTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LfTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(HORIZONTAL);
    }

    public void initData(ArrayList<TabItem> tabs,OnTabClickListener listener){
        this.tabs = tabs;
        this.listener = listener;
        LinearLayout.LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        if (tabs != null && tabs.size() > 0) {
            tabCount = tabs.size();
            TabView mTabView = null;
            for (int i = 0; i < tabs.size(); i++) {
                mTabView = new TabView(getContext());
                mTabView.setTag(tabs.get(i));
                mTabView.initData(tabs.get(i));
                mTabView.setOnClickListener(this);
                addView(mTabView,params);
            }
        } else {
            throw new IllegalArgumentException("tabs can not be empty");
        }
    }

    /**
     * 设置当前选中的TabView
     * @param position 位置
     */
    public void setCurrentTab(int position){
        if (position < tabCount && position >=0){
            View view = getChildAt(position);
            if (selectedView != view){
                view.setSelected(true);
                if (selectedView != null){
                    selectedView.setSelected(false);
                }
                selectedView = view;
            }
        }
    }

    public void onDataChanged(int position,int badgeCount){
        if (position < tabCount && position>=0){
            TabView view = (TabView) getChildAt(position);
            view.onDataChanged(badgeCount);
        }
    }

    @Override
    public void onClick(View v) {
        listener.onTabClick((TabItem) v.getTag());
    }

    public interface OnTabClickListener{
        void onTabClick(TabItem tabItem);
    }
}
