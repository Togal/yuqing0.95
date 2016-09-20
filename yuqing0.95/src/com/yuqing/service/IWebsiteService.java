package com.yuqing.service;

import java.util.List;

import com.yuqing.model.Website;

public interface IWebsiteService {

	public List<Website> getAllWebsite();

	public int getOpinionCountById(Integer webSiteId);
	
}
