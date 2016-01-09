#Android沉浸式状态栏SystemBarTint

## 项目地址
https://github.com/jgilfelt/SystemBarTint

## 使用方法
1. add the dependency in your build.gradle
	
		compile 'com.readystatesoftware.systembartint:systembartint:1.0.3'
2. 布局中添加属性

		android:background="#25C4A4"//指定颜色
		//下面这两个属性一定要填的
	    android:fitsSystemWindows="true"
	    android:clipToPadding="false"
3. 代码中使用

		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
	            setTranslucentStatus(true);
	        }
	
	        SystemBarTintManager tintManager = new SystemBarTintManager(this);
	        tintManager.setStatusBarTintEnabled(true);
	        tintManager.setStatusBarTintResource(R.color.statusbar_bg);//通知栏所需颜色
	
	    }
	
	    @TargetApi(19)
	    private void setTranslucentStatus(boolean on) {
	        Window win = getWindow();
	        WindowManager.LayoutParams winParams = win.getAttributes();
	        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
	        if (on) {
	            winParams.flags |= bits;
	        } else {
	            winParams.flags &= ~bits;
	        }
	        win.setAttributes(winParams);
	    }

##相关的网址
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0922/3495.html