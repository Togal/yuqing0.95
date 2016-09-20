package com.yuqing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.yuqing.model.Power;
import com.yuqing.model.Role;
import com.yuqing.service.IModuleService;
import com.yuqing.service.IPowerService;
import com.yuqing.service.IRoleService;

public class PowerAction {
	
	private IRoleService roleService;
	private IModuleService moduleService;
	private IPowerService powerService;

	private List<Role> roles;

	public String powerManage() {   //获取角色信息
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Role> roles = roleService.findAll();
		request.setAttribute("roles", roles);
		return "ok";
	}

	public String queryRole() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Role> roles = roleService.findAll();
		request.setAttribute("roles", roles);
		return "ok";
	}
	public void updatePower() throws IOException {    //更新权限信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Integer powerId =  Integer.parseInt(request.getParameter("powerId"));
		String op = request.getParameter("op");
		Integer value = Integer.parseInt(request.getParameter("value"));
		
		powerService.updatePower(powerId, op, value);
		response.setContentType("text/plain;charset=gbk");
		PrintWriter out = response.getWriter();
		String msg="";
		Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(date);
		if (value == 1) {
			msg = time + "成功获得"+op+"权限";
		}else {
			msg = time + "成功取消"+op+"权限";
		}
		out.println(msg);
		out.flush();
		out.close();
	}
	
	
	public String queryByRole() throws IOException {     //根据角色查询权限
		HttpServletRequest request = ServletActionContext.getRequest();
		String roleId =  request.getParameter("rid");
		Role role = new Role();
		roleService.findRoleById(Integer.parseInt(roleId));
		List<Power> powers = powerService.findByRoleId(Integer.parseInt(roleId));
		request.setAttribute("powers", powers);
		request.setAttribute("roles", role);
		return "ok";
	}
	
	public IPowerService getPowerService() {
		return powerService;
	}

	public void setPowerService(IPowerService powerService) {
		this.powerService = powerService;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
