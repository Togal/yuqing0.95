package com.yuqing.tools;

import java.io.UnsupportedEncodingException;

public class Str {

	/**
	 * 截取字符串的长度
	 * @param old 需要处理的字符串
	 * @param maxLength 新字符串的最大长度
	 * @return 返回新的字符串
	 */
	public static String sub(String old,int maxLength) {
		old = old.trim();
		String str = null;
		if(old.length()>maxLength-3) {
			str = old.substring(0, maxLength-3);
			str+="...";
		}else {
			str = old;
		}
		return str;
	}
	
	public static String tranEncoding(String old) {
		String str = null;
		try {
			str = new String(old.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
}
