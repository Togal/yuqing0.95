package com.yuqing.service;

import java.util.List;

import com.yuqing.model.Role;

public interface IRoleService {
	

	public List findAll();

	public void addRole(Role role);

	public void delRole(Role role);
	
	public List findNoAdmin();
	public Role findRoleById(Integer id);
}
