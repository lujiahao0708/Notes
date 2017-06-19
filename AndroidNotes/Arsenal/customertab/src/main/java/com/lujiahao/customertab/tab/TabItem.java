package com.lujiahao.customertab.tab;

import com.lujiahao.customertab.fragment.BaseFragment;

/**
 * Created by lujiahao on 2016/5/23.
 */
public class TabItem {
    /**
     * icon
     */
    public int imageResId;
    /**
     * 文本
     */
    public int lableResId;

    public Class<? extends BaseFragment> tagFragmentClz;

    public TabItem(int imageResId, int lableResId,Class<? extends BaseFragment> tagFragmentClz) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
        this.tagFragmentClz = tagFragmentClz;
    }
}
