package com.lujiahao.myutils;

import java.util.Random;

import android.text.TextUtils;

/**
 * 随机字符串工具类
 * 
 * @author 路家豪
 * 
 *         改编自： http://seniorzhai.github.io/2014/08/08/RandomUtils%E9%9A%8F%E6
 *         %9C%BA%E5 %B7%A5%E5%85%B7%E7%B1%BB/
 * 
 */
public class RandomUtils {
	// 制定字符串范围的因子
	public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERS = "0123456789";
	public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 获取随机数字、字母字符串
	 * 
	 * @param length
	 *            需要生成的字符串的长度
	 * @return 包含数字和字母的随机字符串
	 */
	public static String getRandomNumbersAndLetters(int length) {
		return getRandom(NUMBERS_AND_LETTERS, length);
	}

	/**
	 * 获取随机数字字符串
	 * 
	 * @param length
	 *            需要生成的字符串的长度
	 * @return 数字字符串
	 */
	public static String getRandomNumbers(int length) {
		return getRandom(NUMBERS, length);
	}

	/**
	 * 获取英文字母组成的随机字符串
	 * 
	 * @param length
	 *            需要生成的字符串的长度
	 * @return 英文字母组成的随机字符串
	 */
	public static String getRandomLetters(int length) {
		return getRandom(LETTERS, length);
	}

	/**
	 * 获取大写字母组成的随机字符串
	 * 
	 * @param length
	 *            需要生成的字符串的长度
	 * @return 大写字母组成的随机字符串
	 */
	public static String getRandomCapitalLetters(int length) {
		return getRandom(CAPITAL_LETTERS, length);
	}

	/**
	 * 获取小写字母组成的随机字符串
	 * 
	 * @param length
	 *            需要生成的字符串的长度
	 * @return 小写字母组成的随机字符串
	 */
	public static String getRandomLowerCaseLetters(int length) {
		return getRandom(LOWER_CASE_LETTERS, length);
	}

	/**
	 * 获取制定字符串和长度的随机字符串
	 * 
	 * @param source
	 * @param length
	 * @return
	 */
	public static String getRandom(String source, int length) {
		return TextUtils.isEmpty(source) ? null : getRandom(
				source.toCharArray(), length);
	}

	/**
	 * 获取制定字符数组和长度的随机字符串
	 * 
	 * @param sourceChar
	 * @param length
	 * @return
	 */
	public static String getRandom(char[] sourceChar, int length) {
		if (sourceChar == null || sourceChar.length == 0 || length < 0) {
			return null;
		}
		StringBuilder str = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			str.append(sourceChar[random.nextInt(sourceChar.length)]);
		}
		return str.toString();
	}
	
	/**
	 * 获取0~max范围的随机int
	 * 
	 * @param max
	 *            最大值
	 * @return 随机Int值
	 */
	public static int getRandom(int max) {
		return getRandom(0, max);
	}
	
	/**
	 * 获取min~max范围的随机int
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return min~max范围的随机int
	 */
	public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }
}