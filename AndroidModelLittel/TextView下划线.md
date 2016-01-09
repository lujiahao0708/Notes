# TextView下划线

看图说话

![QQ截图20160109174132.png](https://ooo.0o0.ooo/2016/01/09/5690d6770bf0e.png)

## 方法一(笨方法)
    textView.setText(Html.fromHtml("<u>"+"0123456"+"</u>"));

## 方法二(还行)
    textView.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
    textView.getPaint().setAntiAlias(true);//抗锯齿
    
## 扩展
    
    textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
    setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰 
    textView.getPaint().setFlags(0);  // 取消设置的的划线