package com.yuqing.service;

import java.util.List;

import com.yuqing.model.MailBox;

public interface IMailBoxService {
	
	public List<MailBox> getFindAll();

	public boolean mailBoxDel(Integer m);

	public void addMailBox(MailBox mailBox);
	
}
