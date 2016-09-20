package com.yuqing.model;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Role role;
	private String name;
	private String password;
	private String phone;
	private String nick;
	private String area;
	private Set mailBoxes = new HashSet(0);
	private Set interests = new HashSet(0);
	private Set logs = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Role role, String name, String password,String phone, String area) {
		this.role = role;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.area = area;
	}

	/** full constructor */
	public User(Role role, String name, String password,String phone, String nick,
			String area, Set mailBoxes, Set interests, Set logs) {
		this.role = role;
		this.name = name;
		this.password = password;
		this.nick = nick;
		this.area = area;
		this.phone = phone;
		this.mailBoxes = mailBoxes;
		this.interests = interests;
		this.logs = logs;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}


	public Set getMailBoxes() {
		return this.mailBoxes;
	}

	public void setMailBoxes(Set mailBoxes) {
		this.mailBoxes = mailBoxes;
	}

	public Set getInterests() {
		return this.interests;
	}

	public void setInterests(Set interests) {
		this.interests = interests;
	}

	public Set getLogs() {
		return this.logs;
	}

	public void setLogs(Set logs) {
		this.logs = logs;
	}

}