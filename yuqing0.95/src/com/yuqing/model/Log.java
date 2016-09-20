package com.yuqing.model;

import java.util.Date;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */

public class Log implements java.io.Serializable {

	// Fields

	private Integer logId;
	private User user;
	private String operation;
	private Date time;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** full constructor */
	public Log(User user, String operation, Date time) {
		this.user = user;
		this.operation = operation;
		this.time = time;
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}