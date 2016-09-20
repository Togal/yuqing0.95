package com.yuqing.model;

/**
 * MailBox entity. @author MyEclipse Persistence Tools
 */

public class MailBox implements java.io.Serializable {

	// Fields

	private Integer emailId;
	private User user;
	private String emailName;
	private String emailAddress;
	private Short tof;

	// Constructors

	/** default constructor */
	public MailBox() {
	}

	/** full constructor */
	public MailBox(User user, String emailName, String emailAddress, Short tof) {
		this.user = user;
		this.emailName = emailName;
		this.emailAddress = emailAddress;
		this.tof = tof;
	}

	// Property accessors

	public Integer getEmailId() {
		return this.emailId;
	}

	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmailName() {
		return this.emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Short getTof() {
		return this.tof;
	}

	public void setTof(Short tof) {
		this.tof = tof;
	}

}