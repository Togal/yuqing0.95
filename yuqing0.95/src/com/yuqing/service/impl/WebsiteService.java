package com.yuqing.service.impl;

import java.util.List;

import com.yuqing.dao.WebsiteDAO;
import com.yuqing.model.Website;
import com.yuqing.service.IWebsiteService;

public class WebsiteService implements IWebsiteService{

	private WebsiteDAO websiteDao;

	public WebsiteDAO getWebsiteDao() {
		return websiteDao;
	}

	public void setWebsiteDao(WebsiteDAO websiteDao) {
		this.websiteDao = websiteDao;
	}

	public List<Website> getAllWebsite() {
		return websiteDao.getAll();
	}

	public int getOpinionCountById(Integer webSiteId) {
		return websiteDao.getOpinionCountById(webSiteId);
	}
	
	
	

}
