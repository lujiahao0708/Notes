
#DDL数据定义语言

用于定义SQL模式,基本表,视图和索引的创建和撤消操作.

## 对数据库操作
### 创建数据库
	
	create database [if not exists] 数据库名称 [character set 字符集] [collate 比较依据];
	默认使用mysql服务器安装时设置编码：UTF8
	create database day13;  创建数据库
	create database day13 character set utf8；  创建指定编码的数据库
	create database day13 collate utf8_general_ci;  数据将按照“utf8_general_ci”进行比较（校对）。
### 查询数据库创建语句
	
	show create database 数据库名称;
### 删除数据库
	
	drop database [if exists] 数据库名称;
### 修改数据库
	
	alter database 数据库名  character set 字符集 collate 比较方式
	(不建议修改)

## 对表结构操作
### 创建表

	create table 表名(
		字段名称  字段类型  [字段约束],
		字段名称  字段类型  [字段约束],
		....
	);
			
	eg : create table `user` (
			id int primary key,
			firstname varchar(32),
			age int,
			count int
		 );

### 修改字段类型
	alter table 表名 modify 字段名称  新类型
	eg : alter table `user` modify firstname varchar(50);
### 修改字段名称
	alter table 表名 change old字段  new字段名称 new字段类型。
	eg : alter table `user` change firstname username varchar(100);
### 添加字段
	alter table 表名 add column 字段名称 字段类型
	eg : alter table `user` add column sex int;
### 删除字段
	alter table 表名 drop [column] 字段名称
	eg : alter table `user` drop column sex;
### 重命名
	alter table 表名 rename [to] 新表名;
	eg : alter table `user` rename to `t_user`;
### 删除表
	drop table 表名;
	eg : drop table `t_user`;

