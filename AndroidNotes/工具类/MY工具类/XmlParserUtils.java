package com.lujiahao.myutils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class XmlParserUtils {
	/**
	 * 解析xml文件
	 * 
	 * @param <T>
	 * 
	 * @param in
	 *            输入流
	 * @return 存储解析xml文件内容的List集合
	 */
	public static <T> List<T> parserXml(InputStream in) {
		try {
			List<T> newlists = null;
			T t = null;

			// (1)先获取到xmlpullparser解析器 
			XmlPullParser parser = Xml.newPullParser();

			// (2)设置XmlPullParser 解析的参数 
			parser.setInput(in, "utf-8");
			// (3)获取事件的类型 
			int eventType = parser.getEventType();

			// (4)不断循环向下解析 
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					// (5)具体判断一下是哪个开始标签
					if ("channel".equals(parser.getName())) {
						// 创建list集合
						newlists = new ArrayList<T>();
					} else if ("item".equals(parser.getName())) {
						// 创建T实例
						// t = new T();
					} else if ("title".equals(parser.getName())) {
						String nextText = parser.nextText();

					} else if ("description".equals(parser.getName())) {
						// t.setDescription(parser.nextText());

					} else if ("image".equals(parser.getName())) {
						// t.setImage(parser.nextText());

					} else if ("type".equals(parser.getName())) {
						// t.setType(parser.nextText());

					} else if ("comment".equals(parser.getName())) {
						// t.setComment(parser.nextText());

					}
					break;

				case XmlPullParser.END_TAG:
					if ("item".equals(parser.getName())) {
						//把news对象加入到集合中
						newlists.add(t);
					}
					break;
				}
				// 解析下一个事件
				eventType = parser.next();
			}
			return newlists;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
