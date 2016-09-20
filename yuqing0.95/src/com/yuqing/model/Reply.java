package com.yuqing.model;

import java.util.Date;


public class Reply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Opinion opinion;
	private String commentid;
	private String content;
	private String usernick;
	private Date time;
	private Integer parent;
	private Integer up;
	private String region;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** full constructor */
	public Reply(Opinion opinion, String commentid, String content,
			String usernick, Date time, Integer parent, Integer up,
			String region) {
		this.opinion = opinion;
		this.commentid = commentid;
		this.content = content;
		this.usernick = usernick;
		this.time = time;
		this.parent = parent;
		this.up = up;
		this.region = region;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Opinion getOpinion() {
		return this.opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	public String getCommentid() {
		return this.commentid;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsernick() {
		return this.usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getParent() {
		return this.parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getUp() {
		return this.up;
	}

	public void setUp(Integer up) {
		this.up = up;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}