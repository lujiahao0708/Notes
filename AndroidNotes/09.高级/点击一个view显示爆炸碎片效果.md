##项目地址
>https://github.com/tyrantgit/ExplosionField
>
>相似的项目：https://github.com/kot32go/MIUIv6-UninstallAnimation

##使用方法
1. In your build.gradle:
		
		dependencies {
			compile 'tyrantgit:explosionfield:1.0.0'
		}
2. 代码

		public class MainActivity extends Activity {
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_main);
		        // 复位按钮使用的是全部的布局的
		        final LinearLayout root = (LinearLayout) findViewById(R.id.root);
		        // 添加到该界面中
		        final ExplosionField mExplosionField = ExplosionField.attach2Window(this);
		
		        final ImageView iv_test = (ImageView) findViewById(R.id.iv_test);
		        iv_test.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {
		                // 为view设置监听
		                mExplosionField.explode(iv_test);
		            }
		        });
		
		        // 复位按钮
		        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {
		                reset(root);
		                mExplosionField.clear();
		            }
		        });
	    	}
		    private void reset(View root) {
		        if (root instanceof ViewGroup) {
		            ViewGroup parent = (ViewGroup) root;
		            for (int i = 0; i < parent.getChildCount(); i++) {
		                reset(parent.getChildAt(i));
		            }
		        } else {
		            root.setScaleX(1);
		            root.setScaleY(1);
		            root.setAlpha(1);
		        }
		    }
		}
##布局文件
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical"
	    android:gravity="center"
	    android:id="@+id/root"
	    tools:context=".MainActivity">
	    <ImageView
	        android:id="@+id/iv_test"
	        android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"
	        android:src="@mipmap/ic_launcher" />
	    <Button
	        android:text="复位按钮"
	        android:id="@+id/btn_reset"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content" />
	</LinearLayout>