package com.yuqing.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Module entity. @author MyEclipse Persistence Tools
 */

public class Module implements java.io.Serializable {

	// Fields

	private Integer moduleId;
	private String name;
	private String moduleUrl;
	private Module parent;
	private Set modules = new HashSet(0);
	private Set powers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Module() {
	}

	/** minimal constructor */
	public Module(String name) {
		this.name = name;
	}

	/** full constructor */
	public Module(String name,String moduleUrl,Module parent,Set modules, Set powers) {
		this.name = name;
		this.powers = powers;
		this.moduleUrl = moduleUrl;
		this.parent = parent;
		this.modules = modules;
	}

	// Property accessors

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getPowers() {
		return this.powers;
	}

	public void setPowers(Set powers) {
		this.powers = powers;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	public Set getModules() {
		return modules;
	}

	public void setModules(Set modules) {
		this.modules = modules;
	}
}