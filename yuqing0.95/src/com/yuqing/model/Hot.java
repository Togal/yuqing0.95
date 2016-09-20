package com.yuqing.model;

import java.sql.Date;

/**
 * Hot entity. @author MyEclipse Persistence Tools
 */

public class Hot implements java.io.Serializable {

	// Fields

	private Integer hotId;
	private String word;
	private Date hotTime;
	private Integer hotValue;

	// Constructors

	/** default constructor */
	public Hot() {
	}

	/** full constructor */
	public Hot(String word, Date hotTime, Integer hotValue) {
		this.word = word;
		this.hotTime = hotTime;
		this.hotValue = hotValue;
	}

	// Property accessors

	public Integer getHotId() {
		return this.hotId;
	}

	public void setHotId(Integer hotId) {
		this.hotId = hotId;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Date getHotTime() {
		return this.hotTime;
	}

	public void setHotTime(Date hotTime) {
		this.hotTime = hotTime;
	}

	public Integer getHotValue() {
		return this.hotValue;
	}

	public void setHotValue(Integer hotValue) {
		this.hotValue = hotValue;
	}

}