package com.lujiahao.customertab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lujiahao.customertab.R;

/**
 * Created by yx on 16/4/3.
 */
public class ProfileFragment extends BaseFragment   {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_layout, container, false);
        return view;
    }


}
