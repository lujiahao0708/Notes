##如何把一个应用做成Launcher
代码
		
	<intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
        <category android:name="android.intent.category.HOME"/>
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
	home和default都要配置才能生效
存在问题：
	
	按返回键还是会回到默认的桌面
	解决：屏蔽返回键
		@Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if(keyCode== KeyEvent.KEYCODE_BACK) {
	            return true;
	        }
	        return false;
	    }
	但是要提供方法让用户解除这个主界面，不然怎么操作啊
做出桌面图标
	
	应该是使用packagemanager的功能来获取所有的包信息，这个也能获得icon的信息，这就解决了

主要的还是各种界面动画的特效问题

	那个模仿miui的关机界面的github上面的东西好好弄一下