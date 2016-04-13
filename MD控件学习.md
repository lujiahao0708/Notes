FAB好像自带了padding值，父布局的padding值去掉就好了

上面出现的原因是：fab包裹在framelayout里面好像就有padding的效果

但是一般，都用CoordinatorLayout这个布局来包裹fab，这时fab就没有了padding效果，需要在父布局里面添加padding值
这是如果有snackbar的话就会添加滑动删除的效果，上面那种情况下没有的

上面都没用！！！


http://gold.xitu.io/entry/56c86a1699a6ce005af6ac8b

http://www.jianshu.com/p/1078568e859f#