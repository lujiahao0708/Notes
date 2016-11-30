# TextView跑马灯效果
效果图如下 :

![TextView跑马灯效果.gif](https://ooo.0o0.ooo/2016/11/30/583ea6cc3cc37.gif)
## 有缺陷的实现方式

	<TextView
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:gravity="center"
		android:ellipsize="marquee"
		android:marqueeRepeatLimit="marquee_forever"
		android:singleLine="true"
		android:text="我是黑马小卫士 超强十六核杀毒引擎 带你装逼带你飞带你杵进垃圾堆"
		android:textColor="@android:color/holo_blue_dark"
		android:textSize="14sp" />

属性解释 : 

	android:ellipsize="marquee"//跑马灯效果
	android:marqueeRepeatLimit="marquee_forever"//无限制不间断显示
	android:singleLine="true"//单行显示
这种方案只有在TextView有焦点的时候才会滚动,不满足一般的使用.

## 真正的解决方案
重写TextView中的isFocused()方法,使之直接返回true,表征一直获得焦点

	package com.ljh.mobileguard.ui;
	
	import android.content.Context;
	import android.util.AttributeSet;
	import android.widget.TextView;
	
	/**
	 * Created by lujiahao on 2016/11/30.
	 */
	public class MarqueeTextView extends TextView {
	    public MarqueeTextView(Context context) {
	        super(context);
	    }
	
	    public MarqueeTextView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }
	
	    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
	        super(context, attrs, defStyleAttr);
	    }
	
	    @Override
	    public boolean isFocused() {
	        return true;// 相当于直接默认让这个控件获得焦点
	    }
	}

xml中使用方式：

	<com.ljh.mobileguard.ui.MarqueeTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:ellipsize="marquee"
        android:gravity="center"
        android:marqueeRepeatLimit="marquee_forever"
        android:singleLine="true"
        android:text="我是黑马小卫士 超强十六核杀毒引擎 带你装逼带你飞带你杵进垃圾堆"
        android:textColor="@android:color/holo_blue_dark"
        android:textSize="14sp" />