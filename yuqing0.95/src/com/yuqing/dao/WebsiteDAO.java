package com.yuqing.dao;

import java.util.List;

import com.yuqing.model.Website;

public interface WebsiteDAO {

	public void save(Website transientInstance);
	public void delete(Website persistentInstance);
	public Website findById(java.lang.Integer id);
	public List findByExample(Website instance);
	public List findByProperty(String propertyName, Object value);
	public List findByName(Object name);
	public List findByHomePage(Object homePage);
	public List findByCategory(Object category);
	public List findAll();
	public List<Website> getAll();
	public int getOpinionCountById(Integer webSiteId);
}
