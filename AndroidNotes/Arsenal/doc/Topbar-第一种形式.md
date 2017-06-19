#Topbar的第一种形式
>自己画布局，然后为按钮添加点击事件

## 1.去掉Activity标题
方法一：直接继承主题

![noactionbar.png](https://ooo.0o0.ooo/2016/03/29/56fa812ba4ee5.png)

方法二：自定义标题（当不知道一些标题属性的名称的时候，直接点开副标题就可以看到了）

![打开主题发现.png](https://ooo.0o0.ooo/2016/03/29/56fa812ab735c.png)

根据上述属性，编写自己的主题

![QQ截图20160329213928.png](https://ooo.0o0.ooo/2016/03/29/56fa886d8ca36.png)



## 2.编写标题栏布局topbar.xml

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="60dp"
	    android:background="#55ff2233"
	    android:orientation="horizontal">
	    <Button
	        android:id="@+id/btnLeft"
	        android:text="后退"
	        android:layout_width="wrap_content"
	        android:layout_height="match_parent" />
	    <TextView
	        android:id="@+id/tvMiddle"
	        android:layout_weight="1"
	        android:text="标题"
	        android:textSize="14sp"
	        android:textColor="@android:color/black"
	        android:layout_width="0dp"
	        android:layout_height="wrap_content"
	        android:gravity="center"/>
	    <Button
	        android:id="@+id/btnRight"
	        android:text="按钮"
	        android:layout_width="wrap_content"
	        android:layout_height="match_parent"/>
	</LinearLayout>

## 3.主布局中引用
	<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    tools:context="com.chiahao.topbar.MainActivity">
	
	    <include layout="@layout/topbar" />
	</RelativeLayout>
`include`这个就是专门用来加载其他布局到当前布局的。

## 4.左右按钮添加点击事件
	public class MainActivity extends AppCompatActivity {
	
	    private Button btnLeft,btnRight;
	    private TextView tvMiddle;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        btnLeft = (Button) findViewById(R.id.btnLeft);
	        btnRight = (Button) findViewById(R.id.btnRight);
	        tvMiddle = (TextView) findViewById(R.id.tvMiddle);
	
	        btnLeft.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Toast.makeText(MainActivity.this,"您点击了左侧按钮",Toast.LENGTH_SHORT).show();
	            }
	        });
	        btnRight.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Toast.makeText(MainActivity.this,"您点击了右侧按钮",Toast.LENGTH_SHORT).show();
	            }
	        });
	    }
	}

## 5.运行结果
![QQ截图20160329213928.png](https://ooo.0o0.ooo/2016/03/29/56fa86a1c0346.png)