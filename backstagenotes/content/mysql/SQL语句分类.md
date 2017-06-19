
# SQL语句分类

SQL语句可以分为以下四类 : `数据操作语言(DML)` , `数据定义语言(DDL)` , `数据控制语言(DCL)` , `事务控制语言(TCL)`.

## 数据定义语言(DDL : Data Definition Language)
用于定义SQL模式,基本表,视图和索引的创建和撤消操作.<br/>
主要包含`CREATE` , `ALTER` , `DROP` , `TRUNCATE` , `COMMENT` , `REPLACE(RENAME)`等语句,一般不需要commit等事务操作.

## 数据操作语言(DML : Data Manipulation Language)
由数据库管理系统(DBMS) 提供,用于让用户或程序员使用，实现对数据库中数据的操作.<br/> 
主要包含`SELECT` , `INSERT` , `UPDATE` , `DELETE` , `MERGE` , `CALL` , `EXPLAIN PLAN` , `LOCK TABLE`等语句.

## 数据控制语言(DCL：Data Control Language)
授权用户或用户组操作和访问数据的权限.<br/>
主要包含`GRANT` , `REVOKE`等语句.

## 事务控制语言(TCL：Transaction Control Language)
用于数据库的事务管理,确保被DML语句影响的表的所有行及时得以更新.<br/>
主要包含`SAVEPOINT` , `SET TRANSACTION` , `BEGIN TRANSACTION` , `COMMIT` , `ROLLBACK`等语句。

## 非官方分法
##数据查询语言(DQL : Data Queries Language)
用以从表中获得数据.<br/>
主要包含`SELECT` , `WHERE` , `GROUP BY` , `HAVING`和`ORDER BY`.



