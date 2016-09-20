package com.yuqing.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.yuqing.model.Role;
import com.yuqing.service.IRoleService;

public class RoleAction {

		private IRoleService roleService;
		
		public String roleManage() {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<Role> roleList = roleService.findAll();
			request.setAttribute("roleList", roleList);
			return "ok";
		}
		
		public String  roleAddAction() {
			HttpServletRequest request = ServletActionContext.getRequest();
			String roleName = request.getParameter("roleName");
			Role role = new Role();
			role.setName(roleName);
			roleService.addRole(role);
			List<Role> roleList = roleService.findAll();
			request.setAttribute("roleList", roleList);
			return "ok";
		}
		
		public String roleDelAction() {
			HttpServletRequest request = ServletActionContext.getRequest();
			String roleId = request.getParameter("roleId");
			Role role = new Role();
			role.setRoleId(Integer.parseInt(roleId));
			role.setName(roleId);
			roleService.delRole(role);
			List<Role> roleList = roleService.findAll();
			request.setAttribute("roleList", roleList);
			return "ok";	
		}
		public IRoleService getRoleService() {
			return roleService;
		}

		public void setRoleService(IRoleService roleService) {
			this.roleService = roleService;
		}
}
