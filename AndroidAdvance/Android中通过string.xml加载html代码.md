这个不同于webview加载网页，这个是加载一些文字，通过html来布局的东西

##代码
	String html_content = mContext.getString(R.string.pay_two);
	et_content.setText(Html.fromHtml(html_content));

##string.xml中的定义
>其实也可以自定义这个xml的名字，比如：instructions_strings.xml

	<string name="pay_two">
     <Data>
         <![CDATA[<font color="#ff0000" ><b>Q：怎么进入点餐页面？<br></b></font>
         <font color="#00ff00">点击“趣吃饭”APP底部导航“首页”，选择餐厅即可进入点餐页面。<br><br></font>
        <font color="#ff0000"><b>Q：如何快速找到经常光顾的店家或喜欢的菜品？<br></b></font>
        <font color="#00ff00">你可以在导航栏“我的”->“我的收藏”选择进入已收藏的店铺，或者在导航栏“订单”中选择经常购买的菜品。<br><br></font>
        <font color="#ff0000"><b>Q：如对菜品有特殊要求应该写在哪里？<br></b></font>
        <font color="#00ff00">在您支付订单的时候（待支付状态），在待支付页面有“备注”一栏，点击可输入特殊要求。<br><br></font>
        <font color="#ff0000"><b>Q：支付失败怎么办？<br></b></font>
        <font color="#00ff00">在没有网络或支付账户余额不足而生成待支付订单时，可在“我的订单”页查看。把以上问题解决后可继续付款。<br><br></font>
        <font color="#ff0000"><b>Q：订单支付成功后多久可取餐？<br></b></font>
        <font color="#00ff00">订单支付成功后，商家会立刻备餐（通常5到10分钟即可完成备餐），备餐完毕后会发提醒短信同时订单状态一栏显示状态为“备餐完毕”，此时可马上取餐。您可以在前往餐厅的路上用手机完成下单，五分钟的路程刚好供餐厅完成配餐，到餐厅后，直接到取餐柜前取出订好的菜品坐下享用了。<br><br></font>
        <font color="#ff0000"><b>Q：什么情况下订单会被取消？<br></font>
        <font color="#00ff00">1）您选择的菜品无货时，商家执行退单，订单会被取消；<br>2）商家由于其他原因无法接单时会取消订单；<br>3）未支付订单30分钟内未完成支付，订单会被取消。<br><br></font>
        <font color="#ff0000"><b>Q：如果发生退单，退款金额多久可以返回支付账户？<br></font>
        <font color="#00ff00">1-15个工作日内。</font>
         ]]>
     </Data>
    </string>