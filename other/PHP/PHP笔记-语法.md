# php笔记

## 语法

### php格式
	<?php 
	    echo "hello php";
	?>

### php区分大小写
1. 变量区分大小写
2. 常量通常默认也区分，但可设定不区分（不推荐）
3. 其他关键字都不区分，eg：函数名，系统关键字（for，if，return...）

### 语句使用分号结束
1. 在一个php语句中最后一个分号可以省略

		<?php 
		    echo "hello php <br />";
		    echo "hello php2"
		?>
2. php结束标记省略时最后一个分号不能省略

		<html>
			<h1>我是h1文字</h1>
		</html>
		<?php 
		    echo "hello php <br />";
		    echo "hello php2";

### 注释
#### 单行注释
1. // 注释内容
2. # 注释内容

#### 多行注释
/* 注释内容*/
> 技巧：当需要将打断代码多次进行“注释”或“反注释”时使用

1. 第一种技巧

	![aaa.png](https://ooo.0o0.ooo/2016/02/17/56c424f0dc4eb.png)

2. 第二种技巧

	![php注释1.png](https://ooo.0o0.ooo/2016/02/17/56c425b30816b.png)