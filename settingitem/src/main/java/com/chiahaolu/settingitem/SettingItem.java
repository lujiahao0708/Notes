package com.chiahaolu.settingitem;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by lujiahao on 2016/5/25.
 */
public class SettingItem extends LinearLayout {
    private ImageView mIvLeftIcon,mIvRightIcon;
    private TextView mTvLeft,mTvRight;
    private int leftIconResId,rightIconResId;
    private float leftIconWidth,leftIconHeight,rightIconWidth,rightIconHeight;
    private float leftIconMarginLeft,rightIconMarginRight;// 左侧图片的左边距,右侧图标的右边距
    private boolean leftIconIsShow,rightIconIsShow;

    private boolean leftTextIsShow,rightTextIsShow;
    private String leftText,rightText ;
    private int leftTextColor,rightTextColor;
    private float leftTextSize,rightTextSize;
    private float leftTextMarginLeft,rightTextMarginRight;// 左侧文字的左边距,右侧文字的右边距

    public SettingItem(Context context) {
        super(context);
        initView();
    }

    public SettingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.SettingItem);
        leftIconIsShow = ta.getBoolean(R.styleable.SettingItem_leftIconIsShow,true);// 默认图标显示
        leftIconResId = ta.getResourceId(R.styleable.SettingItem_leftIconResId,0);
        leftIconWidth = ta.getLayoutDimension(R.styleable.SettingItem_leftIconWidth,0);
        leftIconHeight = ta.getLayoutDimension(R.styleable.SettingItem_leftIconHeight,0);
        leftIconMarginLeft = ta.getLayoutDimension(R.styleable.SettingItem_leftIconMarginLeft,0);

        leftTextIsShow = ta.getBoolean(R.styleable.SettingItem_leftTextIsShow,true);// 默认显示文字

        leftText = ta.getString(R.styleable.SettingItem_leftText);
        leftTextColor = ta.getColor(R.styleable.SettingItem_leftTextColor,0);
        leftTextSize = ta.getDimensionPixelSize(R.styleable.SettingItem_leftTextSize,0);
        leftTextMarginLeft = ta.getDimensionPixelSize(R.styleable.SettingItem_leftTextMarginLeft,0);

        rightIconIsShow = ta.getBoolean(R.styleable.SettingItem_rightIconIsShow,true);// 默认图标显示
        rightIconResId = ta.getResourceId(R.styleable.SettingItem_rightIconResId,0);
        rightIconWidth = ta.getLayoutDimension(R.styleable.SettingItem_rightIconWidth,0);
        rightIconHeight = ta.getLayoutDimension(R.styleable.SettingItem_rightIconHeight,0);
        rightIconMarginRight = ta.getLayoutDimension(R.styleable.SettingItem_rightIconMarginRight,0);

        rightTextIsShow = ta.getBoolean(R.styleable.SettingItem_rightTextIsShow,true);// 默认显示文字
        rightText = ta.getString(R.styleable.SettingItem_rightText);
        rightTextColor = ta.getColor(R.styleable.SettingItem_rightTextColor,0);
        rightTextSize = ta.getDimensionPixelSize(R.styleable.SettingItem_rightTextSize,0);
        rightTextMarginRight = ta.getDimensionPixelSize(R.styleable.SettingItem_rightTextMarginRight,0);

        ta.recycle();

        initView();

        if (leftIconIsShow) {
            mIvLeftIcon.setImageResource(leftIconResId);
            LayoutParams leftIconParams = new LayoutParams((int) leftIconWidth, (int) leftIconHeight);
            leftIconParams.gravity = Gravity.CENTER_VERTICAL;
            leftIconParams.setMargins((int) leftIconMarginLeft,0, 0,0);
            addView(mIvLeftIcon, leftIconParams);
        }

        LayoutParams textContentParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textContentParams.weight = 1;
        textContentParams.gravity = Gravity.CENTER_VERTICAL;
        LinearLayout textContent = new LinearLayout(getContext());
        if (leftTextIsShow) {
            mTvLeft.setText(leftText);
            mTvLeft.setTextColor(leftTextColor);
            mTvLeft.setTextSize(LayoutUtil.getDIPByPixel(getContext(), (int) leftTextSize));
            LayoutParams leftTextParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            leftTextParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
            leftTextParams.weight = 1;
            leftTextParams.setMargins((int) leftTextMarginLeft, 0, 0, 0);
            mTvLeft.setGravity(Gravity.LEFT);
            textContent.addView(mTvLeft, leftTextParams);
        }
        if (rightTextIsShow) {
            mTvRight.setText(rightText);
            mTvRight.setTextColor(rightTextColor);
            mTvRight.setTextSize(LayoutUtil.getDIPByPixel(getContext(), (int) rightTextSize));
            LayoutParams rightTextParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rightTextParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
            rightTextParams.weight = 1;
            rightTextParams.setMargins(0, 0, (int) rightTextMarginRight, 0);
            mTvRight.setGravity(Gravity.RIGHT);
            textContent.addView(mTvRight, rightTextParams);
        }
        addView(textContent,textContentParams);

        if (rightIconIsShow) {
            mIvRightIcon.setImageResource(rightIconResId);
            LayoutParams rightIconParams = new LayoutParams((int) rightIconWidth, (int) rightIconHeight);
            rightIconParams.gravity = Gravity.CENTER_VERTICAL;
            rightIconParams.setMargins(0,0, (int) rightIconMarginRight,0);
            addView(mIvRightIcon, rightIconParams);
        }
    }

    private void initView() {
        setOrientation(HORIZONTAL);
        mIvLeftIcon = new ImageView(getContext());
        mTvLeft = new TextView(getContext());
        mTvRight = new TextView(getContext());
        mIvRightIcon = new ImageView(getContext());
    }

    public void setLeftIcon(int leftIconResId){
        if (leftTextIsShow){
            mIvLeftIcon.setImageResource(leftIconResId);
        }
    }
    public void setLeftText(String leftText){
        if (leftTextIsShow){
            mTvLeft.setText(leftText);
        }
    }
    public void setRightText(String rightText){
        if (rightTextIsShow){
            mTvRight.setText(rightText);
        }
    }
    public void setRightIcon(int rightIconResId){
        if (rightTextIsShow){
            mIvRightIcon.setImageResource(rightIconResId);
        }
    }
}
