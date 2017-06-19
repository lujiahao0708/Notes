## 前言
> 当初转后台的时候Spring看的太快了,都没有看明白,现在算是弥补以前的技术债吧!<br/>
> 这些笔记来自开涛大神的[《跟我学spring3》](http://jinnianshilongnian.iteye.com/blog/1482071)和黑马课件.<br/>
> 感谢一切让我成长!

## IOC介绍

Spring是于2003 年兴起的一个轻量级的Java 开发框架.

### 功能
- Spring 能帮我们根据配置文件创建及组装对象之间的依赖关系.
- Spring 面向切面编程能帮助我们无耦合的实现日志记录，性能统计，安全控制。
- Spring 能非常简单的帮我们管理数据库事务。
- Spring 还提供了与第三方数据访问框架（如Hibernate、JPA）无缝集成，而且自己也提供了一套JDBC访问模板，来方便数据库访问。
- Spring 还提供与第三方Web（如Struts、JSF）框架无缝集成，而且自己也提供了一套Spring MVC框架，来方便web层搭建。
- Spring 能方便的与Java EE（如Java Mail、任务调度）整合，与更多技术整合（比如缓存框架）。

### 结构图

![spring体系结构.jpg](https://ooo.0o0.ooo/2017/06/01/593006cb02e69.jpg)

## IOC容器原理
> IOC : 控制反转<br/>
> 将对象的创建权,交于spring,由spring来创建.<br/>
> 之后需要new对象,不再自己new,直接从spring容器中获得.

![](http://sishuok.com/forum/upload/2012/2/19/2e57867020e686671fde7923f891ab69__%E6%9C%AA%E6%A0%87%E9%A2%98-5.jpg)

## IOC例子
### 创建工程
![1.png](https://ooo.0o0.ooo/2017/06/01/59300d82d961c.png)

![2.png](https://ooo.0o0.ooo/2017/06/01/59300d830760a.png)
### 导入jar包
![3.png](https://ooo.0o0.ooo/2017/06/01/59300d830a97f.png)
### 设置资源文件夹
![4.png](https://ooo.0o0.ooo/2017/06/01/59300d840c78b.png)
![5.png](https://ooo.0o0.ooo/2017/06/01/59300d85cca8a.png)
### 设置Spring
![6.png](https://ooo.0o0.ooo/2017/06/01/59300d84b619b.png)
![7.png](https://ooo.0o0.ooo/2017/06/01/59300d845c2e1.png)
### 将jar包加入classpath中
![9.png](https://ooo.0o0.ooo/2017/06/01/59300d85afc63.png)
### 编写配置文件和测试类
![8.png](https://ooo.0o0.ooo/2017/06/01/59300d85b636e.png)
![10.png](https://ooo.0o0.ooo/2017/06/01/59300d8570645.png)