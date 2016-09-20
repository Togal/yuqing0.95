package com.yuqing.service;

import java.util.List;

import com.yuqing.model.Module;
import com.yuqing.model.Role;


public interface IModuleService {
	
	public List findAll();

	public List<Module> getByRoleId(Integer roleId);
	
	public void saveModule(Module m);
	
	public List findModuleParentName();
	
	public void delModule(Module module) ;
	
	
	public Module findModuleById(Integer moduleId);
}
