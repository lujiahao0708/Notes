package com.lujiahao.myutils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtils {
	/**
	 * 使用内存输出流(ByteArrayOutputStream)将输入流(InputStream)转换成字符串
	 * @param in 用户传入的输入流
	 * @return	字符串
	 * @throws Exception 
	 */
	public static String readStream(InputStream in) throws Exception{
		//内存输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bys = new byte[1024];
		int len = 0;
		while((len = in.read(bys)) != -1){
			baos.write(bys, 0, len);
		}
		in.close();
		// 可设置转换的字符串的编码格式
		String str = new String(baos.toString().getBytes(), "utf-8");
		return str;
	}
}
