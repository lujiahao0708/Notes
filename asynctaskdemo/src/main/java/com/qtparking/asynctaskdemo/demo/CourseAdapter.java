package com.qtparking.asynctaskdemo.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qtparking.asynctaskdemo.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by chiahao on 2016/4/7.
 */
public class CourseAdapter extends BaseAdapter {
    private List<Course> mList;
    private LayoutInflater mInflater;

    public CourseAdapter(Context context, List<Course> mList) {
        this.mList = mList;
        this.mInflater = LayoutInflater.from(context);
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
        holder.ivIcon.setBackgroundResource(R.mipmap.ic_launcher);
        holder.tvName.setText(mList.get(i).getName());
        holder.tvDes.setText(mList.get(i).getDescription());
        holder.tvLearner.setText(String.valueOf(mList.get(i).getLearner()));
        return view;
    }

    class ViewHolder{
        ImageView ivIcon;
        TextView tvName,tvDes,tvLearner;
    }
}
