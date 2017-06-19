##SwipeBackLayout
2015年9月28日17:44:42
>https://github.com/ikew0ng/SwipeBackLayout

##使用方法
1. gradle
	
		compile 'me.imid.swipebacklayout.lib:library:1.0.0'
2. 将你的Activity继承`SwipeBackActivity`
3. 代码

		public class BaseActivity extends SwipeBackActivity {
			// 把mSwipeBackLayout设置成protected的，这样子类中就可以直接使用了
		    protected SwipeBackLayout mSwipeBackLayout;
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
		
		        // 滑动返回
		        mSwipeBackLayout = getSwipeBackLayout();
		        // 设置滑动模式
		        // 	SwipeBackLayout.EDGE_LEFT 右滑返回
				//	SwipeBackLayout.EDGE_RIGHT	左滑返回
				//	SwipeBackLayout.EDGE_BOTTOM	底部滑动返回
				//	SwipeBackLayout.EDGE_ALL	所有都可以滑动返回
		        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
		    }
		}
4. 解决滑动黑屏问题

	在style中的theme中添加`<item name="android:windowIsTranslucent">true</item>`

	但是在魅族手机中好像有些问题呐  为什么呢？？？
5. 当activity不需要侧滑关闭
	
		mSwipeBackLayout.setEnableGesture(false);