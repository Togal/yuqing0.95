package com.yuqing.tools;

/**
 * js工具类
 * @author xhq
 * @date 2014/3/13
 */
public class JS {
	
	/**
	 * js弹出框
	 * @param title 
	 * @param msg
	 * @return
	 */
	public static String alert(String title,String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=text/javascript>alert('")
			.append(title)
			.append("','")
			.append(msg)
			.append("');</script>");
		return sb.toString();
	}
}
