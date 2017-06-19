讲DDL语言的时候讲到了这个知识点


字符串：
				char(n) 固定字符串，例如：char(5) 表示可以存放5个字符，且必须是5个。
					如果插入 “abc”,结果“abc  ”  右边自动添加空格。
				varchar(n) 可变长字符串，例如：varchar(5),表示最多存放5个字符，如果不够就原样存放。
					如果插入“abc”，结果“abc”
			数字
				bit			比特
				tinyint		byte
				mediumint	short
				int			int		【】
				bigint		long
				float			float
				double(m,d)	double 【】 --m数字长度，d精度及小数位
				numeric		Number	所有数字
  				例如：double(5,2) 5表示整个数字为5位，2表示小数位2个。最大值。999.99
			时间日期
				** 之后使用java日期时间类型：java.util.Date 。如果要使用java.sql..类型，只能存放dao层
				date 日期				java.sql.Date
				datetime 日期时间			---
				time 时间				java.sql.Time
				timestamp 时间戳			java.sql.Timestamp
					 
					sql转util ： java.util.Date date = new java.sql.Date(long);
					util转sql ： new java.sql.Date(  new java.util.Date().getTime()  )
					
			大数据：
				字节：存放二进制  （java.sql.Blob ：Binary Large Object 二进制大对象）
					TINYBLOB  255
					blob		64k
					longblob	4G
				字符：存放文本 （java.sql.Clob ：Character Large Object 字符大对象）
					TINYTEXT	255
					text		64k
					longtext 4G