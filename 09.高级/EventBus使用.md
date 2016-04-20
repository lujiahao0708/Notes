摘自博客：http://blog.csdn.net/harvic880925/article/details/40660137

EventBus是一款针对Android优化的发布/订阅事件总线。主要功能是替代Intent,Handler,BroadCast在Fragment，Activity，Service，线程之间传递消息.优点是开销小，代码更优雅。以及将发送者和接收者解耦。
## 使用方法
1. 下载EventBus的类库
	
		源码：https://github.com/greenrobot/EventBus
		或者在Gradle中配置：compile 'de.greenrobot:eventbus:2.4.0'

2. 自定义一个类

		public class FirstEvent {
		    private String mMsg;
		    public FirstEvent(String mMsg) {
		        this.mMsg = mMsg;
		    }
		    public String getMsg(){
		        return mMsg;
		    }
		}

3. 在要接收消息的页面注册
	
		// 注册EventBus
	    EventBus.getDefault().register(this);

4. 在要发送消息的地方发送消息

		// 发送消息
        EventBus.getDefault().post(new FirstEvent("第二个Activity的按钮被点击了，发送消息到了第一个Activity中"));

5. 接受消息的页面实现(共有四个函数，各功能不同，这是其中之一，可以选择性的实现，这里先实现一个)：

		public void onEventMainThread(FirstEvent event) {
		    String msg = "onEventMainThread收到了消息：" + event.getMsg();
		    Log.d("harvic", msg);
		    tv_content.setText(msg);
		    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		}

6. 在注册的类中解除注册

		// 反注册EventBus
        EventBus.getDefault().unregister(this);

---

##四种订阅函数  一会要进行测试！！！！！

1. onEvent
	>如果使用onEvent作为订阅函数，那么该事件在哪个线程发布出来的，onEvent就会在这个线程中运行，也就是说发布事件和接收事件线程在同一个线程。使用这个方法时，在onEvent方法中不能执行耗时操作，如果执行耗时操作容易导致事件分发延迟。

2. onEventMainThread
	>如果使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行，这个在Android中是非常有用的，因为在Android中只能在UI线程中跟新UI，所以在onEvnetMainThread方法中是不能执行耗时操作的。

3. onEventBackground
	>如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的，那么onEventBackground函数直接在该子线程中执行。

4. onEventAsync
	>使用这个函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程在执行onEventAsync.

##注意
当发过来一个消息的时候，EventBus怎么知道要调哪个函数呢，就看哪个函数传进去的参数是这个类的实例，哪个是就调哪个。那如果有两个是呢，那两个都会被调用。