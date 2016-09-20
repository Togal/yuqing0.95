package com.yuqing.model;

import java.util.Date;

/**
 * Opinion entity. @author MyEclipse Persistence Tools
 */

public class Opinion implements java.io.Serializable {

	// Fields

	private Integer opinionId;
	private Website website;//属于哪个网站
	private String title;
	private String author;
	private Date publishTime;
	private String keyword;//关键词
	private String content;//文章内容
	private String url;//改文章url
	private Date catchTime;//抓取时间
	private String area;//所属地区，本地舆情时有用
	private Integer level;//舆情等级，如危机舆情
	private String referenceUrl;
	private String referenceName;
	private Integer weight;

	// Constructors

	/** default constructor */
	public Opinion() {
	}

	/** minimal constructor */
	public Opinion(Website website, String title, String keyword,
			String content, String url, Date catchTime, String area,
			Integer level) {
		this.website = website;
		this.title = title;
		this.keyword = keyword;
		this.content = content;
		this.url = url;
		this.catchTime = catchTime;
		this.area = area;
		this.level = level;
	}

	/** full constructor */
	public Opinion(Website website, String title, String author,
			Date publishTime, String keyword, String content, String url,
			Date catchTime, String area, Integer level) {
		this.website = website;
		this.title = title;
		this.author = author;
		this.publishTime = publishTime;
		this.keyword = keyword;
		this.content = content;
		this.url = url;
		this.catchTime = catchTime;
		this.area = area;
		this.level = level;
	}

	// Property accessors

	public Integer getOpinionId() {
		return this.opinionId;
	}

	public void setOpinionId(Integer opinionId) {
		this.opinionId = opinionId;
	}

	public Website getWebsite() {
		return this.website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCatchTime() {
		return this.catchTime;
	}

	public void setCatchTime(Date catchTime) {
		this.catchTime = catchTime;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getReferenceUrl() {
		return referenceUrl;
	}

	public void setReferenceUrl(String referenceUrl) {
		this.referenceUrl = referenceUrl;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	
}