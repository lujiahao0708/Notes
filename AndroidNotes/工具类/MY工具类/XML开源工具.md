#XML
- XStream -- 生成xml文件
	1. 拷贝xstream-1.4.7.jar到libs目录下
	2. 点击project structure-->Dependencies-->加号-->File Dependency-->xstream-1.4.7.jar
	3. 开始写代码
	
			// 创建XStream对象
			XStream xStream = new XStream();
			// 创建JavaBean对象，并为其赋值
	        User user = new User();
	        user.setName("张三");
	        user.setAge(20);
	        user.setAddress("中国北京");
			// 输出流写入SD卡
	        FileOutputStream out = null;
	        try {
	            out = new FileOutputStream(new File(Environment.getExternalStorageDirectory(),"user.xml"));
				// 调用方法将JavaBean对象转换成xml文件
	            xStream.toXML(user,out);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	4. 生成的文件

			<com.example.chiahao.myapplication.bean.User>
			  <address>中国北京</address>
			  <name>张三</name>
			  <age>20</age>
			</com.example.chiahao.myapplication.bean.User>