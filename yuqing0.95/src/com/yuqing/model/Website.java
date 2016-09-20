package com.yuqing.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Website entity. @author MyEclipse Persistence Tools
 */

public class Website implements java.io.Serializable {

	// Fields

	private Integer webSiteId;
	private String name;
	private String homePage;//主页
	private Integer category;//所属分类，如：属于文章，微博
	private Set opinions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Website() {
	}

	/** minimal constructor */
	public Website(String name, String homePage, Integer category) {
		this.name = name;
		this.homePage = homePage;
		this.category = category;
	}

	/** full constructor */
	public Website(String name, String homePage, Integer category, Set opinions) {
		this.name = name;
		this.homePage = homePage;
		this.category = category;
		this.opinions = opinions;
	}

	// Property accessors

	public Integer getWebSiteId() {
		return this.webSiteId;
	}

	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHomePage() {
		return this.homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Set getOpinions() {
		return this.opinions;
	}

	public void setOpinions(Set opinions) {
		this.opinions = opinions;
	}

}