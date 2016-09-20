package com.yuqing.service.impl;

import java.util.List;

import com.yuqing.dao.RoleDAO;
import com.yuqing.model.Role;
import com.yuqing.service.IRoleService;

public class RoleService implements IRoleService{
	
	private RoleDAO roleDao;

	public List findAll() {
		return roleDao.findAll();
	}
	
	public RoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	public void addRole(Role role) {
		this.roleDao.save(role);
	}
	
	public void delRole(Role role) {
		this.roleDao.delete(role);
	}

	@Override
	public List findNoAdmin() {

		return this.roleDao.findNoAdmin();
	}

	@Override
	public Role findRoleById(Integer id) {
		return this.roleDao.findById(id);
	}

	
	
}
