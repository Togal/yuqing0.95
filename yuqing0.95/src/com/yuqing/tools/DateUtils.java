package com.yuqing.tools;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author xhq
 * @date 2014/3/13
 */
public class DateUtils {
	
	/**
	 * 日期增长
	 * @param date 开始日期
	 * @param day 增长天数  负数表示减小
	 * @return 增长后的天数
	 */
	public static Date add(Date date,int day) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, day);
		return ca.getTime();
	}
}
