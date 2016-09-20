package com.yuqing.tools;

/**
 * 数字处理类
 * @author xhq
 * @date 2014/3/13
 */
public class Digital {

	/**
	 * 转换数字
	 * @param str 要转换的字符串
	 * @param def 出错时默认返回的数字
	 * @return
	 */
	public static int conversion(String str,int def) {
		int res = 0;
		try {
			res = Integer.parseInt(str);
		}catch(Exception e) {
			res = def;
		}
		return res;
	}
}
