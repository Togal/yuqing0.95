package com.yuqing.dao;

import java.util.List;

public interface BaseHibernateDAO {
	public List listByCustom(String sql,int start,int maxCount);//自定义条件的分页查询
}
