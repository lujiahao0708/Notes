## Activity和Fragment通信总结
- Handler
- 广播
- 接口回调
- Eventbus

[一篇非常不错的文章](http://www.jianshu.com/p/1b824e26105b/comments/1343805#)，总结了这四种方法，但是文章里面提出来的方法现在还看不懂。

## 详细说说EventBus方案
> EventBus 是一个 Android 事件发布/订阅框架，通过解耦发布者和订阅者简化 Android 事件传递。

### 使用方法

[官方主页](https://github.com/greenrobot/EventBus/)

[其他文章](http://www.jianshu.com/p/3f83507b07b4)一大堆一大堆


### 然而这里有个很重要的概念要明白
> EventBus只能传递到已经存在的Activity或Fragment,达到数据的改变,而不能传递数据到一个新建的Activity/Fragment。
> 
> Eventbus是必须先订阅，才能传值。

订阅啥意思？ 

当然是等着接收了，就好比你订了份杂志，首先你得存在，不然杂志即便是发送了也不知道发到哪里。所以，你的新的Activity/Fragment还没有生成，就是还没有订阅成功，即便是你post了值，也是接收不到滴！


我当时一直没法post数据到新的Activity的时候内心是这样的

[![](http://7xjzdd.com1.z0.glb.clouddn.com/i/2016-02-13-03bdb9db1d2597a622de16fe3b2f26cc.)](http://zhuangbi.info/)

## 后记
Activity和Fragment之间的通信的确比较麻烦，不过通过使用EventBus很好的解决了这一问题，虽然是反射的原理，但是这点资源的消耗还是可以忽略不计的。