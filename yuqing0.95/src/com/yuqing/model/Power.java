package com.yuqing.model;


public class Power implements java.io.Serializable {

	// Fields

	private Integer powerId;
	private Role role;
	private Module module;
	private Integer add;
	private Integer del;
	private Integer update;
	private Integer find;

	// Constructors

	/** default constructor */
	public Power() {
	}

	/** minimal constructor */
	public Power(Role role, Module module) {
		this.role = role;
		this.module = module;
	}

	/** full constructor */
	public Power(Role role, Module module, Integer add, Integer del,
			Integer update, Integer find) {
		this.role = role;
		this.module = module;
		this.add = add;
		this.del = del;
		this.update = update;
		this.find = find;
	}

	public Integer getPowerId() {
		return powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Integer getAdd() {
		return add;
	}

	public void setAdd(Integer add) {
		this.add = add;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public Integer getUpdate() {
		return update;
	}

	public void setUpdate(Integer update) {
		this.update = update;
	}

	public Integer getFind() {
		return find;
	}

	public void setFind(Integer find) {
		this.find = find;
	}
	
	



}