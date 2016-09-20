package com.yuqing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.yuqing.model.Module;
import com.yuqing.model.Power;
import com.yuqing.model.Role;
import com.yuqing.service.IModuleService;
import com.yuqing.service.IPowerService;
import com.yuqing.service.IRoleService;

public class ModuleAction {

	private  IModuleService moduleService;
	private IRoleService roleService;
	private IPowerService powerService;
	
	

	public String moduleManage() {
		HttpServletRequest request =  ServletActionContext.getRequest();
		List<Role> roleList = roleService.findNoAdmin();
		List<Module> moduleList = moduleService.findAll();
		List<Power> powerList = powerService.findAll();
		List<Module> moduleParent = moduleService.findModuleParentName();
		request.setAttribute("roleList", roleList);
		request.setAttribute("moduleList", moduleList);
		request.setAttribute("moduleParent", moduleParent);
		request.setAttribute("powerList", powerList);
		return "ok";
	}
	
	public String moduleAddAction() {
		System.out.println("moduleAdd");
		HttpServletRequest request = ServletActionContext.getRequest();
		String moduleName = request.getParameter("moduleName");
		String parentId = request.getParameter("mp");
		String moduleUrl = request.getParameter("moduleUrl");
		String flag = request.getParameter("flagCheck");
		Module module =new Module();
		
		module.setName(moduleName);
		if (moduleUrl != null) {
			module.setModuleUrl(moduleUrl);
		}
		if (flag.equals("trueCheck")) {
			Module parent =new Module();
			parent.setModuleId(Integer.parseInt(parentId));
			module.setParent(parent);
		}
		moduleService.saveModule(module);
		moduleManage();
		return "ok";
	}
	
	public String moduleDelAction() {
		System.out.println("moduleDelAction");
		HttpServletRequest request = ServletActionContext.getRequest();
		String moduleId = request.getParameter("moduleId");
		Module module = new Module();
//		module.setModuleId(Integer.parseInt(moduleId));
//		module.setName("name");
		module = moduleService.findModuleById(Integer.parseInt(moduleId));
		moduleService.delModule(module);
		moduleManage();
		return "ok";
	}

	public void addModuleByRole() throws IOException {
		System.out.println("addModuleByRole");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String moduleId = request.getParameter("moduleId").trim();
		String roleId  = request.getParameter("roleId").trim();
		System.out.println("moduleId="+moduleId+"roleId=" + roleId);
		Module module = new Module();
		module = moduleService.findModuleById(Integer.parseInt(moduleId));
		System.out.println("module ====" + module.getName());
		Role role = new Role();
		role = roleService.findRoleById(Integer.parseInt(roleId));
		System.out.println("role==" + role.getName());
		Power power = new Power();
		power.setModule(module);
		power.setRole(role);
		power.setFind(1);
		power.setDel(0);
		power.setUpdate(0);
		power.setAdd(0);
		powerService.addModuleRole(power);
		
		response.setContentType("text/plain;charset=gbk");
		PrintWriter out = response.getWriter();
		String msg="";
		Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(date);
		
		msg = role.getName() + "成功获得"+module.getName()+"模块";
		out.println(msg);
		out.flush();
		out.close();
	}
	
	
	public void cancelModuleByRole() throws IOException {
		System.out.println("cancelModuleByRole");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String moduleId = request.getParameter("moduleId").trim();
		String roleId  = request.getParameter("roleId").trim();
		System.out.println("moduleId="+moduleId+"roleId=" + roleId);
		Module module = new Module();
		module = moduleService.findModuleById(Integer.parseInt(moduleId));
		System.out.println("module ====" + module.getName());
		Role role = new Role();
		role = roleService.findRoleById(Integer.parseInt(roleId));
		System.out.println("role==" + role.getName());
		Power power = new Power();
		power.setModule(module);
		power.setRole(role);
		System.out.println("1" +power.getRole().getName() );
		
		powerService.deleteModuleRole(power);
		
		response.setContentType("text/plain;charset=gbk");
		PrintWriter out = response.getWriter();
		String msg="";
		
		msg = role.getName() + "取消获得"+module.getName()+"模块";
		out.println(msg);
		out.flush();
		out.close();
	}
	
	public IModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(IModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IPowerService getPowerService() {
		return powerService;
	}

	public void setPowerService(IPowerService powerService) {
		this.powerService = powerService;
	}

	
}
