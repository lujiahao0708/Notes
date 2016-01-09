package com.lujiahao.mobilesafe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密实现
 * MD5特性：
 * 不可逆
 * 明文转化成密文后，密文不能转化成明文
 * 这种加密方式不仅仅限于密码加密，也可以用于文件加密等
 * Created by chiahao on 2015/7/19.
 */
public class MD5Utils {
    /**
     * 密码MD5加密
     *
     * @param pwd 要加密的密码
     * @return 加密之后的字符串
     */
    public static String digestPassword(String pwd) {
        // 准备一个sb来存储加密后的字符串
        StringBuilder sb = new StringBuilder();
        try {
            // 获得数据摘要器
            //      参数：加密方式
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 将要加密的byte数组加密，返回一个加密过后的byte数组
            //      参数:要加密的数组
            // 这是第一次加密，为后面做准备
            byte[] digest = messageDigest.digest(pwd.getBytes());
            for (int i = 0; i < digest.length; i++) {
                // 将一个byte类型的数据与0xff与运算可以将负数转化成正数
                // byte：-128--127
                int result = digest[i] & 0xff;
                // 然后将int类型的值转化成十六进制字符串
                String hexString = Integer.toHexString(result);
                // 转化后的字符串如果少于两位的话就添加一个0来补齐
                if (hexString.length() < 2) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // 找不到加密方式异常
            e.printStackTrace();
            return null;
        }
    }
}
