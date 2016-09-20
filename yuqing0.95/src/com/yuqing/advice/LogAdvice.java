package com.yuqing.advice;

import org.apache.struts2.ServletActionContext;

import com.yuqing.model.User;
import com.yuqing.service.ILogService;

public class LogAdvice{

	private ILogService logService;
	
	public void loginAfter(User user) {
		if(user != null) {
			String ip = ServletActionContext.getRequest().getRemoteAddr();
			logService.addLoginLog(user, ip);
			System.out.println("成功加入登录日志");
		}
	}

	public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}
}
