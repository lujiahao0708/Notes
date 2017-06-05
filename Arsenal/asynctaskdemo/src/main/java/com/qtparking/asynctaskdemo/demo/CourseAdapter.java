package com.qtparking.asynctaskdemo.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.qtparking.asynctaskdemo.R;

import java.util.List;

/**
 * Created by chiahao on 2016/4/7.
 */
public class CourseAdapter extends BaseAdapter implements AbsListView.OnScrollListener{
    private List<Course> mList;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;

    private int mStart,mEnd;
    public static String[] URLS;
    private boolean mFirstIn;

    public CourseAdapter(Context context, List<Course> mList,ListView listView) {
        this.mList = mList;
        this.mInflater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader(listView);
        URLS = new String[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            URLS[i] = mList.get(i).getPicSmall();
        }
        mFirstIn = true;
        // 一定要注册这个事件
        listView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_course,null);
            holder.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.tvDes = (TextView) view.findViewById(R.id.tvDes);
            holder.tvLearner = (TextView) view.findViewById(R.id.tvLearner);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        String url = mList.get(i).getPicSmall();
        holder.ivIcon.setTag(url);
        //mImageLoader.showImageByThread(holder.ivIcon, url);
        mImageLoader.showImageByAsycnTask(holder.ivIcon, url);
        holder.tvName.setText(mList.get(i).getName());
        holder.tvDes.setText(mList.get(i).getDescription());
        holder.tvLearner.setText(String.valueOf(mList.get(i).getLearner()));
        return view;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // listview滑动状态切换调用
        if (scrollState == SCROLL_STATE_IDLE){
            // 停止状态  加载可见项
            mImageLoader.loadImages(mStart,mEnd);
        } else {
            // 停止所有加载任务
            mImageLoader.cancelAllTasks();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 滑动时调用
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        if (mFirstIn && visibleItemCount > 0){
            mImageLoader.loadImages(mStart,mEnd);// 手动调用  第一次显示的时候调用
            mFirstIn = false;
        }
    }

    class ViewHolder{
        ImageView ivIcon;
        TextView tvName,tvDes,tvLearner;
    }
}
