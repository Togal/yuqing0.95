package com.yuqing.model;

import java.util.Date;

/**
 *用于兴趣的趋势统计    基于时间排序
 * @author Administrator
 *
 */
public class ReplyCountAndTime implements Comparable<ReplyCountAndTime>{
	private Integer count;
	private Date replyTtime;
	
	public ReplyCountAndTime(){};
	
	public ReplyCountAndTime(Integer count, Date replyTtime) {
		super();
		this.count = count;
		this.replyTtime = replyTtime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Date getReplyTtime() {
		return replyTtime;
	}
	public void setReplyTtime(Date replyTtime) {
		this.replyTtime = replyTtime;
	}

	@Override
	public int compareTo(ReplyCountAndTime o) {
		
		return this.replyTtime.getTime()>o.getReplyTtime().getTime()?1:-1;
	}
}
