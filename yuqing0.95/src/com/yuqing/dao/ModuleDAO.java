package com.yuqing.dao;

import java.util.List;

import com.yuqing.model.Module;

public interface ModuleDAO {
	
	public void save(Module m);
	public void delete(Module m);
	public Module findById(Integer id);
	public List findByExample(Module m);
	public List findByName(Object name);
	public List findAll();
	public List<Module> getByRoleId(Integer roleId);
	public List findParentName();
}
