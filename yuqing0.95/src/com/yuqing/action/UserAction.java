package com.yuqing.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.yuqing.constant.Level;
import com.yuqing.model.Interest;
import com.yuqing.model.Module;
import com.yuqing.model.User;
import com.yuqing.service.IInterestService;
import com.yuqing.service.ILogService;
import com.yuqing.service.IModuleService;
import com.yuqing.service.IRoleService;
import com.yuqing.service.IUserService;
import com.yuqing.tools.Digital;
import com.yuqing.tools.Paging;

public class UserAction {
	
	private String name;
	
	private String password;
	
	private IUserService userService;
	
	private ILogService logService;
	
	private IRoleService roleService;
	
	private IInterestService interestService;
	
	private IModuleService moduleService;
	
	
	public IInterestService getInterestService() {
		return interestService;
	}

	public void setInterestService(IInterestService interestService) {
		this.interestService = interestService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
//		String inCode = request.getParameter("validateCode");
//		Object o = session.getAttribute("validateCode");
//		String code = o==null?"validateCodeSpecial":o.toString();
//		if(!code.equalsIgnoreCase(inCode)) {
//			request.setAttribute("msg", "验证码输入错误");
//			return "input";
//		}
		User user = (User)session.getAttribute("user");
		if(user != null){
			List<Module> modules = moduleService.getByRoleId(user.getRole().getRoleId());
			request.setAttribute("modules", modules);
			List<Interest> interests = interestService.getInterestsByUserId(user.getUserId());
			request.setAttribute("interests", interests);
			return "ok";
		}
		
		if(null == name || name.trim().equals("") || null == password || password.trim().equals("")) {
			request.setAttribute("error", 1);
			return "input";
		}
		boolean flag = userService.isExist(name);
		if(flag == false) {//不存在用户名
			request.setAttribute("userNameError", 1);
			return "input";
		}
		User u = userService.login(name, password);
		if(u!=null) {
			session.setAttribute("user",u);
			Integer userId = u.getUserId();
			List<Interest> interests = interestService.getInterestsByUserId(userId);
			request.setAttribute("interests", interests);
			List<Module> modules = moduleService.getByRoleId(u.getRole().getRoleId());
			request.setAttribute("modules", modules);
			return "ok";
		} else {
			request.setAttribute("name", name);
			request.setAttribute("passwordError", 1);
			return "input";
		}
	}
	
	
	public void validateUser() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = request.getParameter("name");
		String password = request.getParameter("password").trim();
		PrintWriter out = response.getWriter();
		
		if(userService.login(name, password)!=null) {
			out.write("ok");
		}else {
			out.write("error");
		}
		out.flush();
		out.close();
	}
	
	public String  modifyPassword() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String error = null;
		if(request.getParameter("oldPwd").equals(u.getPassword())) {
			if(request.getParameter("newPwd").endsWith(request.getParameter("confirmPwd"))){
				u.setPassword(request.getParameter("newPwd"));
				userService.update(u);
				return "ok";
			}else {
				error = "*输入密码不一致";
			}
		}else{
			error = "*原密码错误";
		}
		request.setAttribute("error", error);
		return "error";
	}
	
	public String userManage() {
		System.out.println("userMange");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("userList", userService.findAll());
		request.setAttribute("roleList", roleService.findAll());
		//分页设置
		int PAGESIZE = 6;
		int totalCount = userService.getTotalCount();
		int page = Digital.conversion(request.getParameter("page"), 1);
		String url = request.getRequestURI();
		Paging p = new Paging(url, PAGESIZE, totalCount, Paging.GROUPSIZE);
		request.setAttribute("paging", p.getPage(page));
		return "ok";
	}
	
	
	public void userPowerUpdateAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int userId = Integer.parseInt(request.getParameter("userId"));
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		HttpServletResponse response = ServletActionContext.getResponse();
		userService.updateRoleById(userId,roleId);
		
		PrintWriter p = null;
		try {
			p = response.getWriter();
			p.print("ok");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(p != null) {
				p.close();
				p = null;
			}
		}
	}
	
	
	public String userDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String[] split = u.getArea().split("-");
		request.setAttribute("provice", split[0]);
		request.setAttribute("city", split[1]);
		request.setAttribute("hometown", split[2]);
		return "ok";
	}
	
	public String modifyDetailAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String nick = request.getParameter("nick");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String hometown = request.getParameter("hometown");
		String phone = request.getParameter("phone");
		String area = province+"-"+city+"-"+hometown;
		System.out.println("area:"+area);
		u.setArea(area);u.setPhone(phone);u.setNick(nick);
		userService.update(u);
		session.setAttribute("user", u);
		return "ok";
	}
	
	public String operatLog() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int userId = ((User)session.getAttribute("user")).getUserId();
		int page = Digital.conversion(request.getParameter("page"), 1);
		int pageSize = 14;
		int totalCount = logService.getTotalCount(userId);
		String url = request.getRequestURI();
		
		Paging p = new Paging(url, pageSize, totalCount, Paging.GROUPSIZE);
		List logs = logService.get(userId,(page-1)*pageSize,pageSize);
		request.setAttribute("paging", p.getPage(page));
		request.setAttribute("levelMap", Level.levelMap);
		request.setAttribute("logs", logs);
		return "ok";
	}
	
	public String exit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
System.out.println("退出系统");
		return "ok";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	public IModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(IModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	
}
