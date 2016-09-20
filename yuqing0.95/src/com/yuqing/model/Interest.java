package com.yuqing.model;

/**
 * Interest entity. @author MyEclipse Persistence Tools
 */

public class Interest implements java.io.Serializable {

	// Fields
	private Integer id;
	private User user;
	private String content;

	// Constructors

	/** default constructor */
	public Interest() {
	}

	/** minimal constructor */
	public Interest(Integer id, User user) {
		this.id = id;
		this.user = user;
	}

	/** full constructor */
	public Interest(Integer id, User user, String content) {
		this.id = id;
		this.user = user;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}