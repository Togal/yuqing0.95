package com.yuqing.dao;

import java.util.List;

import com.yuqing.model.Hot;

public interface HotDAO {

	public void save(Hot h);
	public void delete(Hot h);
	public Hot findById(Integer id);
	public List findByExample(Hot h);
	public List findByWord(Object word);
	public List findByHotValue(Object hotValue);
	public List findAll();
}
