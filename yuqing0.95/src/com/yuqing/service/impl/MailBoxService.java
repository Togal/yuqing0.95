package com.yuqing.service.impl;

import java.util.List;

import com.yuqing.dao.MailBoxDAO;
import com.yuqing.model.MailBox;
import com.yuqing.service.IMailBoxService;

public class MailBoxService implements IMailBoxService{

	private MailBoxDAO mailBoxDao;
	
	public void setMailBoxDao(MailBoxDAO mailBoxDao){
		this.mailBoxDao = mailBoxDao;
	}
	
	public MailBoxDAO getmailBoxDao(){
		return mailBoxDao;
	}
	
	public List<MailBox> getFindAll(){
		List<MailBox> mailBox = mailBoxDao.findAll();
		return mailBox;
	}
	
	
//	public boolean mailBoxAdd(MailBox mailBox){
//		
//		this.mailBoxDao.add(mailBox);
//		
//		return true;
//	}

	public boolean mailBoxDel(MailBox mailBoxId) {

		this.mailBoxDao.delete(mailBoxId);
		return true;
	}

	public void addMailBox(MailBox mailBox) {
		mailBoxDao.save(mailBox);
	}

	@Override
	public boolean mailBoxDel(Integer m) {
		
		return mailBoxDao.delete(m);
	}
	
}
