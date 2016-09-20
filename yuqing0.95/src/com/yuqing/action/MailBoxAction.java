package com.yuqing.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.yuqing.model.MailBox;
import com.yuqing.model.User;
import com.yuqing.service.IMailBoxService;
import com.yuqing.tools.Digital;

public class MailBoxAction {

	private IMailBoxService mailBoxService;
	
	public String mailBox() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
	
		List<MailBox>  mailBoxList = mailBoxService.getFindAll();
		request.setAttribute("mailBoxList",mailBoxList);
		return "ok";
	}

	public String mailBoxAdd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String emailName = request.getParameter("emailName");
		String emailAddress = request.getParameter("emailAddress");
		
		MailBox mailBox = new MailBox();
		mailBox.setUser((User)session.getAttribute("user"));
		mailBox.setEmailName(emailName);
		mailBox.setEmailAddress(emailAddress);
		mailBox.setTof(Short.parseShort("0"));
		
		mailBoxService.addMailBox(mailBox);
		session.setAttribute("mailBox", mailBox);
		return "ok";
	}
	
	
	public String mailBoxDel(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String mailboxId = request.getParameter("str");
		System.out.println(mailboxId);
		String[] emailIds = mailboxId.split(",");
		for(int i=0;i<emailIds.length;i++) {
		 Integer m = Digital.conversion(emailIds[i], 1);
			mailBoxService.mailBoxDel(m);
		}
		return "ok";
	}

	public IMailBoxService getMailBoxService() {
		return mailBoxService;
	}

	public void setMailBoxService(IMailBoxService mailBoxService) {
		this.mailBoxService = mailBoxService;
	}
}