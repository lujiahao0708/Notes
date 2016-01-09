#WaveCompat使用记录

## 项目地址
[https://github.com/wangjiegulu/WaveCompat](https://github.com/wangjiegulu/WaveCompat)

## 效果图
![](https://github.com/wangjiegulu/WaveCompat/raw/master/screenshot/wave_compat_a.gif)![](https://github.com/wangjiegulu/WaveCompat/raw/master/screenshot/wave_compat_b.gif)

## 使用方法

- 导入相应的库文件
	
	`compile project(':wavecompatlibrary')`
- Gradle添加依赖

	`compile 'com.github.wangjiegulu:AndroidBucket:1.0.2'`

    `compile 'com.github.wangjiegulu:AndroidInject:1.0.2'`
> 这个原作者好像没有写上这个内容，这里是个坑。

- 继承类和实现接口
	
	`public class BaseActivity extends AIActionBarActivity implements WaveTouchHelper.OnWaveTouchHelperListener`
- 初始的时候绑定控件

	`WaveTouchHelper.bindWaveTouchHelper(view, onWaveTouchHelperListener);`控件，监听 
- 重写OnWaveTouchHelperListener接口中的onWaveTouchUp()方法

		@Override
	    public void onWaveTouchUp(View view, Point locationInView, Point locationInScreen) {
	        switch (view.getId()) {
	            case R.id.iv_splash_register:
	                WaveCompat.startWaveFilter(this,
	                        new WaveDrawable()
	                                .setColor(0xff2AA8AA)// 颜色必须要加上透明度
	                                .setTouchPoint(locationInScreen),
	                        startColorActivity(0xff2AA8AA, RegisterActivity.class));
	                break;
	            case R.id.iv_splash_login:
	                WaveCompat.startWaveFilter(this,
	                        new WaveDrawable()
	                                .setColor(0xddffffff)
	                                .setTouchPoint(locationInScreen),
	                        startColorActivity(0xddffffff, LoginActivity.class));
	                break;
	        }
	    }
	
		//启动一个带有背景颜色的Activity
	    public Intent startColorActivity(int color, Class<? extends BaseActivity> clazz) {
	        Intent intent = new Intent(this, clazz);
	        intent.putExtra(WaveCompat.IntentKey.BACKGROUND_COLOR, color);
	        return intent;
	    }